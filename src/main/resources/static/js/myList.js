//10,20,30개씩 selectBox 클릭 이벤트
function changeSelectBox(currentPage, pageSize){
    var selectValue = $("#cntSelectBox").children("option:selected").val();
    movePage(currentPage, selectValue, pageSize); 
}

//페이지 이동
function movePage(currentPage, cntPerPage, pageSize){
    
    let url = "/myList";
    url = url + "?currentPage="+currentPage;
    url = url + "&cntPerPage="+cntPerPage;
    url = url + "&pageSize="+pageSize;
    
	let keyword = $('#keyword').val().trim();
	let srchTyp = $('#srchTyp').val();
 
    if(keyword != null) {
    	url = url + "&srchTyp=" + srchTyp;
   	 	url = url + "&keyword=" + keyword;
    }
    
    location.href=url;
}

function searchKw() {
	let srchTyp = $('#srchTyp').val();
	let keyword = $('#keyword').val().trim();
	let selectValue = $("#cntSelectBox").children("option:selected").val();
	
	 let url = "/myList";
	 url = url + "?srchTyp=" + srchTyp;
	 url = url + "&keyword=" + keyword;
	 url = url + "&cntPerPage=" + selectValue;
	 
	 location.href=url;
}

function searchReset() {
	location.href="/myList";
}

function viewRslt(survNo, currentPage, pageSize) {
	let srchTyp = $('#srchTyp').val();
	let keyword = $('#keyword').val().trim();
	let cntPerPage = $("#cntSelectBox").children("option:selected").val();
	
	let url = "/survRslt";
	url = url + "?survNo=" + survNo;
	url = url + "&currentPage="+currentPage;
	url = url + "&cntPerPage=" + cntPerPage;
    url = url + "&pageSize=" + pageSize;
	url = url + "&srchTyp=" + srchTyp;
	url = url + "&keyword=" + keyword;
	
	location.href = url;
}

function editSurv(survNo, currentPage, pageSize) {
	let srchTyp = $('#srchTyp').val();
	let keyword = $('#keyword').val().trim();
	let cntPerPage = $("#cntSelectBox").children("option:selected").val();
	
	 let url = "/modSurvForm";
	 url = url + "?currentPage="+currentPage;
     url = url + "&cntPerPage="+cntPerPage;
     url = url + "&pageSize="+pageSize;
	 url = url + "&srchTyp=" + srchTyp;
	 url = url + "&keyword=" + keyword;
	 url = url + "&survNo=" + survNo;
	 
	 location.href=url;
}