$(function () {

  $("#addQuestRow").click(function() {
    var lastItemNo = $("#surv_quests_tbl tr:last").attr("class").replace("item","");
    var newItemNo = parseInt(lastItemNo)+1;

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



});

function delQuest(data) {
	$(data).parent().parent().remove();
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
            '<ol id="select-multi-opt" style="list-stlye-type:decimal" start="1">'
           + '	<li id="opt1">'
           + '		<input name="multi-opt" placeholder="옵션" ></input>'
           + '		<span name="opt_delete" style="padding-left:40px" onclick="deleteOpt(\'select\',this);">X<br></span>'
           + '	</li>'
           + '</ol>'
           + '<span name="addOptionBtn" style="text-decoration:underline;" onclick="addOption(\'select\',this);">옵션추가</span>'
     );
  } else if (q_type=='q_radio') {

  } else if (q_type=='q_check') {

  }
}

function addOption(optType, data) {
  let rownum = $(data).closest('tr').prevAll().length;

  if (optType=='select') {
    $("#select-multi-opt").eq(rownum).append(
              '<li>'
            + '<input name="multi-opt" placeholder="옵션" ></input>'
            + '<span name="opt_delete" style="padding-left:40px" onclick="deleteOpt(\"select\", this);">X<br></span>'
            + '</li>'
     );
  }

}