$(function () {

	$("#regDate").text(getToday());
	$("#modDate").text(getToday());

  $("#addQuestRow").click(function() {
    //var lastItemNo = $("#surv_quests_tbl tr:last").attr("class").replace("item","");
    //var newItemNo = parseInt(lastItemNo)+1;
    let newItemNo = ($('#surv_quests_tbl>tbody tr').length) + 1;

    let defaultHtml = '<tr class="item'+newItemNo+'">';
    defaultHtml += '      <td style="text-align:center;">';
    defaultHtml += '          <button id="delQuestRow" onclick="delQuest(this);">삭제</button>';
    defaultHtml += '      </td>';
    defaultHtml += '      <td class="questNo">질문'
    defaultHtml +=		  newItemNo
    defaultHtml += '      </td>';
    defaultHtml += '      <td>';
    defaultHtml += '          <select name="q_type" id="q_type" onchange="showQuest(this)">';
    defaultHtml += '              <option value="q_short">단답형</option>';
    defaultHtml += '              <option value="q_long">장문형</option>';
    defaultHtml += '              <option value="q_select">드롭다운형</option>';
    defaultHtml += '              <option value="q_radio">라디오버튼형</option>';
    defaultHtml += '              <option value="q_check">체크박스형</option>';
    defaultHtml += '          </select>';
    defaultHtml += '      </td>';
    defaultHtml += '      <td>';
    defaultHtml += '          <div class="surv_opt_box">';
    defaultHtml += '              <input id="q_short" placeholder="질문을 입력해주세요">';
    defaultHtml += '          </div>';
    defaultHtml += '      </td>';
    defaultHtml += '  </tr>';


    $("#surv_quests_tbl").append(defaultHtml);
    
    });
    
    
    $("#regSurvBtn").click(function() {
		alert("등록클릭!");
		
		let survqustList=[]; //질문1개 1개 모아놓은 거
		let qustOptDto=[];  //옵션 1개 1개 모아놓은 거
		
		
		$("#surv_quests_tbl>tbody tr").each(function() {
			let survQustObj = new Object (); //질문1개
			let qustOptArr = [];
			
			let q_type = $(this).find("td:eq(2) select option:selected").val();
			survQustObj.qustSeq = $(this).find("td:eq(1)").text().substr(2);
			survQustObj.qustType = q_type;
			
			if(q_type=='q_short') {				
			survQustObj.qustCont = $(this).find("td:eq(3) input").val().trim();
			} else if(q_type=="q_long") {
			survQustObj.qustCont = $(this).find("td:eq(3) textarea").val().trim();
			} else {
				survQustObj.qustCont = $(this).find("td:eq(3) input").val().trim();
				
				$(this).find('ol[name="multi-opt"] li').each(function(index, item) {
					let optObj = new Object(); //옵션 1개의 객체
					
					optObj.optSeq = index+1;
					optObj.optCont = $(item).find("input").val();
					qustOptArr.push(optObj);
				});
				survQustObj.qustoptList = qustOptArr;
			}
			survqustList.push(survQustObj);
			
		});
		
		 var param = {
				"survTitle" : $("#survTitle").val(),
				"memNick" : $("#memNick").text(),
				"regDate" : $("#regDate").text(),
				"useYn" : $("#useYn").val(),		
				"survDesc" : $("#survDesc").val(),
				"survqustList" : survqustList,
			};
			
	
		console.log("param ==> " + JSON.stringify(param));
	
		var header = $("meta[name='_csrf_header']").attr('content');
		var token = $("meta[name='_csrf']").attr('content');
		
	    $.ajax({
			url:'/regSurv',
			type: 'POST',
			contentType : "application/json; charset=utf-8",
			data: JSON.stringify(param),
			beforeSend: function(xhr){
	        xhr.setRequestHeader(header, token);
	    },
			success: function() {
				alert('등록 완료');
			},
			error: function(e) {
				alert("등록 실패!!");
				console.log(e);
			}
		});
  });
   
    
});

function delQuest(data) {
	let rownum = $('#surv_quests_tbl>tbody tr').length;
	var idx = $(data).parent().parent().index();

	if (rownum < 2) {
		alert("질문의 개소는 최소 1개 이상입니다.");
		return;
	} 
	$(data).parent().parent().remove();
	rownum = $('#surv_quests_tbl>tbody tr').length; //마지막번호

	for(var i=idx; i<rownum; i++) {
		$("#surv_quests_tbl>tbody").find("tr:eq("+i+")").find(".questNo").text("질문"+(i+1));
		$("#surv_quests_tbl>tbody").find("tr:eq("+i+")").attr("class","item"+(i+1));
	}
}

function showQuest(type) {
  let q_type = type.value;
  let rownum = $(type).closest('tr').prevAll().length;

  $('.surv_opt_box').eq(rownum).empty();

  if (q_type=='q_short') {
    $('.surv_opt_box').eq(rownum).append(
            '<input id="q_short" placeholder="질문을 입력해주세요">'
     );
  } else if (q_type=='q_long') {
    $('.surv_opt_box').eq(rownum).append(
            '<textarea id="q_long" placeholder="질문을 입력해주세요"></textarea>'
     );
  } else if (q_type=='q_select') {
    $('.surv_opt_box').eq(rownum).append(
			 '<input id="qustCont" placeholder="질문을 입력해주세요">'
           + '<ol id="select-multi-opt" name="multi-opt" style="list-stlye-type:decimal" start="1">'
           + '	<li id="opt1">'
           + '		<input name="multi-opt" placeholder="옵션" ></input>'
           + '		<span name="opt_delete" style="padding-left:40px;display:none;" onclick="deleteOpt(\'select\',this);">X<br></span>'
           + '	</li>'
           + '</ol>'
           + '<span name="addOptionBtn" style="text-decoration:underline;" onclick="addOption(\'select\',this);">옵션추가</span>'
     );
  } else if (q_type=='q_radio') {
	$('.surv_opt_box').eq(rownum).append(
			 '<input id="qustCont" placeholder="질문을 입력해주세요">'
           + '<ol id="radio-multi-opt" name="multi-opt" style="list-stlye-type:decimal" start="1">'
           + '	<li id="opt1">'
           + '		<input name="multi-opt" placeholder="옵션" ></input>'
           + '		<span name="opt_delete" style="padding-left:40px;display:none;" onclick="deleteOpt(\'radio\',this);">X<br></span>'
           + '	</li>'
           + '</ol>'
           + '<span name="addOptionBtn" style="text-decoration:underline;" onclick="addOption(\'radio\',this);">옵션추가</span>'
     );
	} else if (q_type=='q_check') {
	$('.surv_opt_box').eq(rownum).append(
			 '<input id="qustCont" placeholder="질문을 입력해주세요">'
           + '<ol id="check-multi-opt" name="multi-opt" style="list-stlye-type:decimal" start="1">'
           + '	<li id="opt1">'
           + '		<input name="multi-opt" placeholder="옵션" ></input>'
           + '		<span name="opt_delete" style="padding-left:40px;display:none;" onclick="deleteOpt(\'check\',this);">X<br></span>'
           + '	</li>'
           + '</ol>'
           + '<span name="addOptionBtn" style="text-decoration:underline;" onclick="addOption(\'check\',this);">옵션추가</span>'
     );	
	}
}

function addOption(optType, data) {
	var idx = $(data).parent().parent().parent().index();
	let optCnt;
	
  if (optType=='select') {
   $("#surv_quests_tbl>tbody").find("tr:eq("+idx+")").find("#select-multi-opt").append(
              '<li>'
            + '<input name="multi-opt" placeholder="옵션" ></input>'
            + '<span name="opt_delete" style="padding-left:40px;display:none;" onclick="deleteOpt(\'select\',this);">X<br></span>'
            + '</li>'
     );
  } else if (optType=='radio') {
	$("#surv_quests_tbl>tbody").find("tr:eq("+idx+")").find("#radio-multi-opt").append(
              '<li>'
            + '<input name="multi-opt" placeholder="옵션" ></input>'
            + '<span name="opt_delete" style="padding-left:40px;display:none;" onclick="deleteOpt(\'radio\',this);">X<br></span>'
            + '</li>'
     );
	} else if (optType=='check') {
		
	$("#surv_quests_tbl>tbody").find("tr:eq("+idx+")").find("#check-multi-opt").append(
              '<li>'
            + '<input name="multi-opt" placeholder="옵션" ></input>'
            + '<span name="opt_delete" style="padding-left:40px;display:none;" onclick="deleteOpt(\'check\',this);">X<br></span>'
            + '</li>'
     );	
     
	}
	
	optCnt = $(data).siblings('ol').find("li").length;

     if (((optType=='select' || optType=='check') && optCnt>1) || (optType=='radio' && optCnt>2)) {
		$("#surv_quests_tbl>tbody").find("tr:eq("+idx+")").find($("span[name=opt_delete]")).css("display","");
		}

}

function deleteOpt(optType, data) {
  var idx = $(data).closest('tr').index();
  let optCnt = $(data).closest('ol').find("li").length;

  if (optType=='select' || optType=='check') {
	
    if(optCnt > 1) {
		$(data).parent().remove();
    } 
	
  } else if (optType=='radio') {
	 if(optCnt > 2) {
      $(data).parent().remove();
    } 	
  }
  showDelBtn(optType, idx);
  
}

function showDelBtn(optType, idx) {
	let optCnt = $("#surv_quests_tbl>tbody").find("tr:eq("+idx+")").find("li").length;
	
	if (((optType=='select' || optType=='check') && optCnt==1) || (optType=='radio' && optCnt<=2)) {
		$("#surv_quests_tbl>tbody").find("tr:eq("+idx+")").find($("span[name=opt_delete]")).css("display","none");
	}

}

function getToday() {
	var date = new Date();
	var year = date.getFullYear();
	 var month = ("0" + (1 + date.getMonth())).slice(-2);
    var day = ("0" + date.getDate()).slice(-2);

    return year + "-" + month + "-" + day;
}