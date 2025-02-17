// 차트 그리기
function chart(id, type, label, labelList, dataList, formatter, 
                option={'anchor':'end', 'align':'top', 'offset':-30, 'fontSize':14}
              ) {
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
      // responsive: false,
      // maintainAspectRatio: false,
      width: '100%',
      // height: 400,
      
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
          anchor: option.anchor,
          align: option.align,
          offset: option.offset,
          font: {
            weight: 'normal',
            size: option.fontSize
          },
          formatter: (value, ctx) => {
            return formatter(value, ctx); 
          },
        }
      }
    }
  });
}