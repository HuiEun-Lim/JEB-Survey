$(function () {
	
	var header = $("meta[name='_csrf_header']").attr('content');
	var token = $("meta[name='_csrf']").attr('content');
	
	
	$(".surv_opt_box").each(function(index,item) {
		let qustType = $(item).find("ol").attr("id");
		
		let optNum = $(item).find("li").length;
		
		if(qustType!=undefined && (qustType.startsWith("select") || qustType.startsWith("check")) && optNum > 1) {
			$(item).find($("span[name=opt_delete]")).css("display","");
		} else if (qustType!=undefined && qustType.startsWith("radio") && optNum > 2){
			$(item).find($("span[name=opt_delete]")).css("display","");
		}
	})
	
	

	$("#regDate").text(getToday());
	

  $("#addQuestRow").click(function() {
    //var lastItemNo = $("#surv_quests_tbl tr:last").attr("class").replace("item","");
    //var newItemNo = parseInt(lastItemNo)+1;
    let newItemNo = ($('#surv_quests_tbl>tbody tr').length) + 1;

    let defaultHtml = '<tr class="item'+newItemNo+'">';
    defaultHtml += '      <td style="text-align:center;">';
    defaultHtml += '          <button id="delQuestRow" onclick="delQuest(this);">🗑️</button>';
    defaultHtml += '      </td>';
    defaultHtml += '      <td class="questNo">질문'
    defaultHtml +=		  newItemNo
    defaultHtml += '      </td>';
    defaultHtml += '      <td>';
    defaultHtml += '          <select name="qustType" id="qustType" onchange="showQuest(this)">';
    defaultHtml += '              <option value="short">단답형</option>';
    defaultHtml += '              <option value="long">장문형</option>';
    defaultHtml += '              <option value="select">드롭다운형</option>';
    defaultHtml += '              <option value="radio">라디오버튼형</option>';
    defaultHtml += '              <option value="check">체크박스형</option>';
    defaultHtml += '          </select>';
    defaultHtml += '      </td>';
    defaultHtml += '      <td>';
    defaultHtml += '          <div class="surv_opt_box">';
    defaultHtml += '              <input id="qustCont" placeholder="질문을 입력해주세요">';
    defaultHtml += '          </div>';
    defaultHtml += '      </td>';
    defaultHtml += '  </tr>';


    $("#surv_quests_tbl").append(defaultHtml);
    
    });
    
    
    let isSubmitted=false; //ajax 전송 상태
    let changed = false; //내용 변화 상태
    let oldParam;
    
    $(document).on("propertychange change keyup paste input","input, textarea", function(){
		changed=true;
		isSubmitted=false;
		$("#regSurvBtn").removeAttr('disabled');
		console.log("changed => "+changed);
	});
	
	$("#useYn").on("change",function() {
		changed=true;
		isSubmitted=false;
		$("#regSurvBtn").removeAttr('disabled');
	});
	
	if ($("#useYn").val()=='N') {
		$("#uptSurvBtn").attr('disabled','disabled');
		alert('수정할 수 없는 설문입니다!');
	}


    $("#regSurvBtn").click(function() {
		
		let chkValidate = chkFields()==true? true:false;
	
		if(chkValidate) {
			if (!isSubmitted && changed) {
				isSubmitted = true;
				console.log("isSubmitted => "+isSubmitted);                  
				$("#regSurvBtn").attr('disabled','disabled');
				
				let survqustList=[]; //질문1개 1개 모아놓은 거
				
				$("#surv_quests_tbl>tbody tr").each(function() {
					let survQustObj = new Object (); //질문1개
					let qustOptArr = [];
					
					let qustType = $(this).find("td:eq(2) select option:selected").val();
					survQustObj.qustSeq = $(this).find("td:eq(1)").text().substr(2);
					survQustObj.qustType = qustType;
					
					if(qustType=='short') {				
					survQustObj.qustCont = $(this).find("td:eq(3) input").val().trim();
					} else if(qustType=="long") {
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
				
				let param = {
						"survTitle" : $("#survTitle").val(),
						"regDate" : $("#regDate").text(),
						"useYn" : $("#useYn").val(),		
						"survDesc" : $("#survDesc").val(),
						"survqustList" : survqustList,
					};
					
				console.log("param ==> " + JSON.stringify(param));
				
				let chkChangedRslt = chkChanged(oldParam)==true?true:false;
				
				if(chkChangedRslt) {
				
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
							oldParam = param;
							
							if(confirm("My Survey에서 확인할까요?")) {
  					 			location.href = "myList?";
							}
						},
						error: function(e) {
							alert("등록 실패!!");
							console.log("isSubmitted => "+isSubmitted);
							console.log(e);
						},
						complete: function() {
							isSubmitted = false;
						}
					});
				} else {
					alert("이미 등록된 설문입니다.");
				}
			}
		} else {
			alert("값을 입력해주세요!!");
		}
  });
  
  $("#uptSurvBtn").click(function() {
	if(confirm("정말 수정하시겠습니까?")) {
		
		let chkValidate = chkFields()==true? true:false;
	
		if(chkValidate) {
			let survNo = new URL(location.href).searchParams.get('survNo');
			let survqustList=[]; //질문1개 1개 모아놓은 거
			
			$("#surv_quests_tbl>tbody tr").each(function() {
				let survQustObj = new Object (); //질문1개
				let qustOptArr = [];
				
				let qustType = $(this).find("td:eq(2) select option:selected").val();
				survQustObj.qustSeq = $(this).find("td:eq(1)").text().substr(2);
				survQustObj.qustType = qustType;
				
				if(qustType=='short') {				
				survQustObj.qustCont = $(this).find("td:eq(3) input").val().trim();
				} else if(qustType=="long") {
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
					"survNo" : survNo,
					"survTitle" : $("#survTitle").val().trim(),
					"regDate" : $("#regDate").text(),
					"useYn" : $("#useYn").val(),		
					"survDesc" : $("#survDesc").val().trim(),
					"survqustList" : survqustList,
				};
				
			console.log("param ==> " + JSON.stringify(param));
			
		    $.ajax({
				url:'/updateSurv',
				type: 'POST',
				contentType : "application/json; charset=utf-8",
				data: JSON.stringify(param),
				beforeSend: function(xhr){
		        xhr.setRequestHeader(header, token);
		    },
				success: function() {
					alert('수정 완료!!');
					let query = window.location.search;
  					 let param = new URLSearchParams(query);

 					 param.delete("survNo");
  					 location.href = "myList?" + param;
				},
				error: function(e) {
					alert("수정 실패!!");
					console.log(e);
				}
			});
		} else {
			alert("값을 입력해주세요!!");
		}
		
	} else {
		return false;
	}
});
  
  
  $("#delSurvBtn").click(function() {
		if(confirm("정말 삭제하시겠습니까?")) {
			
			let survNo = new URL(location.href).searchParams.get('survNo');
			let currentPage = new URL(location.href).searchParams.get('currentPage');
			console.log("삭제할 survNo : "+survNo, "  currentPage : "+currentPage);
					
			$.ajax({
				url:'/delSurv',
				type: 'POST',
				data: {
					"survNo" : survNo
				},
				beforeSend: function(xhr){
		        xhr.setRequestHeader(header, token);
		        },
		        success: function() {
					alert('삭제 완료');
					 let query = window.location.search;
  					 let param = new URLSearchParams(query);

 					 param.delete("survNo");
  					 location.href = "myList?" + param;
				},
				error: function(e) {
					alert("삭제 실패!!");
					console.log(e);
				}
			});
		} else {
			return false;
		}
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
  let qustType = type.value;
  let rownum = $(type).closest('tr').prevAll().length;

  $('.surv_opt_box').eq(rownum).empty();

  if (qustType=='short') {
    $('.surv_opt_box').eq(rownum).append(
            '<input id="qustCont" type="text"  placeholder="질문을 입력해주세요">'
     );
  } else if (qustType=='long') {
    $('.surv_opt_box').eq(rownum).append(
            '<textarea id="qustCont" type="text"  placeholder="질문을 입력해주세요"></textarea>'
     );
  } else if (qustType=='select') {
    $('.surv_opt_box').eq(rownum).append(
			 '<input id="qustCont" type="text"  placeholder="질문을 입력해주세요">'
           + '<ol id="select-multi-opt" name="multi-opt" style="list-style-type:decimal" start="1">'
           + '	<li id="opt1">'
           + '		<input name="multi-opt" placeholder="옵션" ></input>'
           + '		<span name="opt_delete" style="display:none;" onclick="deleteOpt(\'select\',this);">❌<br></span>'
           + '	</li>'
           + '</ol>'
           + '<span name="addOptionBtn" style="text-decoration:underline;" onclick="addOption(\'select\',this);">옵션추가</span>'
     );
  } else if (qustType=='radio') {
	$('.surv_opt_box').eq(rownum).append(
			 '<input id="qustCont" type="text"  placeholder="질문을 입력해주세요">'
           + '<ol id="radio-multi-opt" name="multi-opt" style="list-style-type:decimal" start="1">'
           + '	<li id="opt1">'
           + '		<input name="multi-opt" placeholder="옵션" ></input>'
           + '		<span name="opt_delete" style="display:none;" onclick="deleteOpt(\'radio\',this);">❌<br></span>'
           + '	</li>'
           + '</ol>'
           + '<span name="addOptionBtn" style="text-decoration:underline;" onclick="addOption(\'radio\',this);">옵션추가</span>'
     );
	} else if (qustType=='check') {
	$('.surv_opt_box').eq(rownum).append(
			 '<input id="qustCont" type="text"  placeholder="질문을 입력해주세요">'
           + '<ol id="check-multi-opt" name="multi-opt" style="list-style-type:decimal" start="1">'
           + '	<li id="opt1">'
           + '		<input name="multi-opt" placeholder="옵션" ></input>'
           + '		<span name="opt_delete" style="display:none;" onclick="deleteOpt(\'check\',this);">❌<br></span>'
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
            + '<input type="text"  name="multi-opt" placeholder="옵션" ></input>'
            + '<span name="opt_delete" style="display:none;" onclick="deleteOpt(\'select\',this);">❌<br></span>'
            + '</li>'
     );
  } else if (optType=='radio') {
	$("#surv_quests_tbl>tbody").find("tr:eq("+idx+")").find("#radio-multi-opt").append(
              '<li>'
            + '<input type="text"  name="multi-opt" placeholder="옵션" ></input>'
            + '<span name="opt_delete" style="display:none;" onclick="deleteOpt(\'radio\',this);">❌<br></span>'
            + '</li>'
     );
	} else if (optType=='check') {
		
	$("#surv_quests_tbl>tbody").find("tr:eq("+idx+")").find("#check-multi-opt").append(
              '<li>'
            + '<input type="text"  name="multi-opt" placeholder="옵션" ></input>'
            + '<span name="opt_delete" style="display:none;" onclick="deleteOpt(\'check\',this);">❌<br></span>'
            + '</li>'
     );	
     
	}
	
	optCnt = $(data).siblings('ol').find("li").length;

     if (((optType=='select' || optType=='check') && optCnt>1) || (optType=='radio' && optCnt>2)) {
		$("#surv_quests_tbl>tbody").find("tr:eq("+idx+")").find($("span[name=opt_delete]")).css("display","");
		}

}

function deleteOpt(optType, data) {
 changed=true;
 isSubmitted=false;
 $("#regSurvBtn").removeAttr('disabled');
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

function chkFields() {
	let result = true;
	$('input[type=text]').each(function(index,item) {
			let txt = $(item).val().trim();
			
			if (""==txt || null==txt) {
				console.log("index: "+index+"  item: "+item);
				result=false;
				return false;
			} 				
			
	});
	$('textarea').each(function(index,item) {
		let txt = $(item).val().trim();
			
			if (""==txt || null==txt) {
				result=false;
				return false;
			}
	});
	if (result) {
		return true;
	} else {
		return false;
	}
}

function chkChanged(oldParam) {
	let newParam;
	let survqustList=[]; //질문1개 1개 모아놓은 거
				
				$("#surv_quests_tbl>tbody tr").each(function() {
					let survQustObj = new Object (); //질문1개
					let qustOptArr = [];
					
					let qustType = $(this).find("td:eq(2) select option:selected").val();
					survQustObj.qustSeq = $(this).find("td:eq(1)").text().substr(2);
					survQustObj.qustType = qustType;
					
					if(qustType=='short') {				
					survQustObj.qustCont = $(this).find("td:eq(3) input").val().trim();
					} else if(qustType=="long") {
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
				
				  newParam = {
						"survTitle" : $("#survTitle").val().trim(),
						"regDate" : $("#regDate").text(),
						"useYn" : $("#useYn").val(),		
						"survDesc" : $("#survDesc").val().trim(),
						"survqustList" : survqustList,
					};
					
	console.log("oldParam => "+JSON.stringify(oldParam));
	console.log("newParam => "+JSON.stringify(newParam));
	if (JSON.stringify(oldParam) === JSON.stringify(newParam)) {
		console.log("oldParam === newParam");
		return false;
	} else {
		console.log("oldParam != newParam");
		return true;
	}
}
