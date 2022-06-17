//10,20,30개씩 selectBox 클릭 이벤트
function changeSelectBox(currentPage, pageSize){
    var selectValue = $("#cntSelectBox").children("option:selected").val();
    movePage(currentPage, selectValue, pageSize); 
}

//페이지 이동
function movePage(currentPage, cntPerPage, pageSize){
    
    let url = "/survList";
    url = url + "?currentPage="+currentPage;
    url = url + "&cntPerPage="+cntPerPage;
    url = url + "&pageSize="+pageSize;
    
    let srchTyp = $('#srchTyp').val();
	let keyword = $('#keyword').val().trim();
 
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
	
	 let url = "/survList";
	 url = url + "?srchTyp=" + srchTyp;
	 url = url + "&keyword=" + keyword;
	 url = url + "&cntPerPage=" + selectValue;
	 
	 location.href=url;
}

function searchReset() {
	location.href="/survList";
}

/*작성자 : Bonnie 리스트 응답 폼 보여주기 */
/* 
 * 수정자 : Jetty
 * 수정 내용 : 접속 회원의 해당 설문 응답 여부 확인 후 응답 페이지로 이동
 */
function resForm(survNo, currentPage,pageSize){
	
	// spring security 토큰/헤더
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
	
	$.ajax({		
		type:'post',
		url:'/checkRes',
		data:{
				"memId" : memId,
				"survNo" : survNo
			},
		beforeSend : function(xhr){
			xhr.setRequestHeader(header, token);
		},
		success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
			 if(cnt != 0){ //cnt가 0이 아니라면 이미 응답한 설문
				alert("이미 응답한 설문입니다.");
			 } else { // cnt가 0일 경우 페이징 정보를 가지고 설문 응답 페이지로 이동
				let srchTyp = $('#srchTyp').val();
				let keyword = $('#keyword').val().trim();
				let selectValue = $("#cntSelectBox").children("option:selected").val();
            	
				let url = "/resForm";
				url += "?survNo="+survNo;
				url += "&currentPage="+currentPage;
				url += "&cntPerPage="+selectValue;
				url += "&pageSize="+pageSize;
				url += "&srchTyp="+srchTyp;
				url += "&keyword="+keyword;
				
				location.href = url;
            }
		console.log("cnt >> " + cnt);
        },
        error:function(){
            alert("에러입니다");
        }
	})

}