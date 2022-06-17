$(document).ready(function(){
	for(let idx in qustList) {
		
		let qust = qustList[idx];
		let qustType = qust.qustType;
		
		// 응답 수
		let total = 0;
		
		if(qustType != "long") {
			for(let answ in qust.answerList) {
				total += qust.answerList[answ].count;
			}
			const count = document.getElementById(qust.qustNo + "total");
			count.innerHTML = total;
		}
		
		// 차트 그리기
		if(qustType != "long" && qustType != "short" && total != 0) {
			const ctx = document.getElementById(qust.qustNo).getContext('2d');
			
			let labels = [];
			let data = [];
			
			for(let answ in qust.answerList) {
				labels.push(qust.answerList[answ].answCont);
				data.push(qust.answerList[answ].count);
			}
			
			// 체크박스일 경우 막대그래프
			if(qustType == "check") {
				const barChart = new Chart(ctx, {
				    type: 'bar',
				    data: {
				        labels: labels,
				        datasets: [{
				            label: qust.qustCont,
				            data: data,
				            backgroundColor: [
				                'rgba(255, 99, 132, 0.4)',
				                'rgba(54, 162, 235, 0.4)',
				                'rgba(255, 206, 86, 0.4)',
				                'rgba(75, 192, 192, 0.4)',
				                'rgba(153, 102, 255, 0.4)',
				                'rgba(255, 159, 64, 0.4)',
				                'rgba(201, 203, 207, 0.4)'
				            ],
				            borderColor: [
				                'rgba(255, 99, 132)',
				                'rgba(54, 162, 235)',
				                'rgba(255, 206, 86)',
				                'rgba(75, 192, 192)',
				                'rgba(153, 102, 255)',
				                'rgba(255, 159, 64)',
				                'rgb(201, 203, 207)'
				            ],
				            borderWidth: 1
				        }]
				    },
				    options: {
				        scales: {
				            y: {
				                beginAtZero: true,
				                ticks: {
				                	/*stepSize : 1,*/
				                	// y축 간격을 정수로
				                	callback: function(value) {if (value % 1 === 0) {return value;}}
								}
				            },
				        }
				    }
				});
				
			// 드롭다운 / 라디오(객관식)일 경우 파이 그래프 
			} else if(qustType == "select" || qustType == "radio") {
				const pieChart = new Chart(ctx, {
				    type: 'pie',
				    data: {
				        labels: labels,
				        datasets: [{
				            data: data,
				            backgroundColor: [
				            	'rgba(255, 99, 132, 0.4)',
				                'rgba(54, 162, 235, 0.4)',
				                'rgba(255, 206, 86, 0.4)',
				                'rgba(75, 192, 192, 0.4)',
				                'rgba(153, 102, 255, 0.4)',
				                'rgba(255, 159, 64, 0.4)',
				                'rgba(201, 203, 207, 0.4)'
				            ],
				            borderColor: [
				                'rgba(255, 99, 132)',
				                'rgba(54, 162, 235)',
				                'rgba(255, 206, 86)',
				                'rgba(75, 192, 192)',
				                'rgba(153, 102, 255)',
				                'rgba(255, 159, 64)',
				                'rgb(201, 203, 207)'
				            ],
				            borderWidth: 1,
				            hoverOffset: 4
				        }]
				    },
				    options: {
				    	legend: {
				            position: 'right',
				            display: true
				        },
				        plugins: {
				            legend: {
				                position: 'right',
				            }
				        }
				    }
				});
				
			} // 질문 유형에 따라 chart 그리는 if문
		} // 장문/단답 확인용 if문
	} // qustList
}); // $(document).ready

function goList(currentPage, cntPerPage, pageSize, srchTyp, keyword) {
	let url = "/myList";
	url = url + "?currentPage=" + currentPage;
	url = url + "&cntPerPage=" + cntPerPage;
	url = url + "&pageSize=" + pageSize;
    url = url + "&srchTyp=" + srchTyp;
 	url = url + "&keyword=" + keyword;
	 	
	location.href=url;
}