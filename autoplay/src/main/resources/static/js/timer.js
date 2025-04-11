$(function() {
  setInterval(async function() {
    const response = await $ajax({
      url: '/timer',
      type: 'GET',
      data: {}
    });
    if (response === true) {
      $alert('알림', '접속 후 10분이 지났습니다. 설문조사에 응해주세요', 'info');
    }
  }, 20 * 1000);
  // 20초마다 서버에 요청을 보내고, 응답이 true일 경우 알림을 띄운다.
})