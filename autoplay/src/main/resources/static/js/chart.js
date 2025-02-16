// 차트 그리기
function chart(id, type, label, labelList, dataList, formatter) {
  ctx = document.getElementById(id).getContext('2d');
  new Chart(ctx, {
    type: type,
    data: {
      labels: labelList,
      datasets: [{
        label: label,
        data: dataList,
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true,
          ticks: {
            maxTicksLimit: 5,
          }
        }
      },
      plugins: {
        datalabels: {
          color: 'black',
          anchor: 'end',
          align: 'top',
          offset: 10,
          font: {
            weight: 'normal',
            size: 14
          },
          formatter: (value, ctx) => {
            return formatter(value); 
          },
        }
      }
    }
  });
}