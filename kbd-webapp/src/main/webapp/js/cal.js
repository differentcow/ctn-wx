var CalModel = function(){
  var self = this;
  var now = new Date();
  this.currY=now.getFullYear();
  this.currM=now.getMonth() + 1;
  this.initEvent = function(){
//    $('.cal_d').on("taphold",function(){
//      console.log("你正在长按"+$(this).data('cal_curdata').date);
//      $('#jy').modal('show')
//    });
      $('.cal_d').longTap(function(){
          //console.log("你正在长按"+$(this).data('cal_curdata').date);
          $('#record_date').html($(this).data('cal_curdata').dateStr);
          $('#jy').modal('show')
      });
      $('.cal_d').tap(function(){
          window.location.href='journey.html?date='+$(this).data('cal_curdata').date.getTime()
      });
//    $('.cal_d').on("tap",function(){
//      console.log("你正在点击"+$(this).data('cal_curdata').date);
////      $('#jy').modal('show')
//    });
//     $('#jy').on("swiperight",function(){
//         console.log("你正在向左划")
//         $('#yl').modal('show');
//     });
      $('#jy').swipeLeft(function(){
         console.log("你正在向左划")
//         $('#yl').modal('show');
//         $('#jy').modal('hide');
          $(".move-c").animate({"left":-268},200);
     });
      $('#jy').swipeRight(function(){
          console.log("你正在向右划")
//          $('#jy').modal('show');
//          $('#yl').modal('hide');
          $(".move-c").animate({"left":-0},200);
      });
//    var dayArray = $('.cal_d');
//    for(var day=0;day<dayArray.length;day++){
//      if(typeof  dayArray[day]!='undefined') {
//        dayArray[day].addEventListener("longtap", function (a,b) {
//          console.log("你正在长按"+$(this).data('cal_curdata').date);
////          $('#jy').modal('show')
//        });
//        dayArray[day].addEventListener("tap", function () {
//          console.log("你正在点击");
//          $('#jy').modal('show');
//
//        });
//      }
//    }
  };

  this.loadCal = function(iyear, imonth){
      var datas = [];
      var wzCount = 2;
      var xcCount = 4;
      var preBykm = 0;
      var preXCDay = 0;
      var xcInterval = 5;
      var preKm = 0;
      var jyInterval = 300;
      var byInterval = 5000;
      for (var i = 0; i < 31; i++) {
          var data = {
              dateStr: iyear + "年" + imonth + "月" + (i + 1)+'日', //日期
              date:new Date(iyear,imonth-1,i+1),
              mileage: Math.round(Math.random() * 100), //里程
              wz: Math.round(Math.random() * 5) == 1, //违章
              xc: Math.round(Math.random() * 5) == 1, //洗车
              by: Math.round(Math.random() * 5) == 1, //保养
              jy: Math.round(Math.random() * 5) == 1//加油
          };
          if (data.wz) {
              if (wzCount - 1 >= 0) {
                  wzCount = wzCount - 1;
              } else {
                  data.wz = false;
              }
          }
          if (data.xc) {
              if (xcCount - 1 >= 0 && (preXCDay == 0 || preXCDay + xcInterval < i + 1)) {
                  preXCDay = i + 1;
                  xcCount = xcCount - 1;
              } else {
                  data.xc = false;
              }
          }

          if(preBykm!=0){
              preBykm += data.mileage;
          }
          if (preBykm == 0 || preBykm >= byInterval) {
              preBykm=0;
              preBykm += data.mileage;
              data.by = true;
          } else {
              data.by = false;
          }

          preKm += data.mileage;
          if (preKm > jyInterval) {
              data.jy = true;
              preKm = 0;
          } else {
              data.jy = false;
          }
          datas.push(data);
      }
    $("#cal").carcal({
      year: iyear,
      month: imonth,
      data: datas,
      yearOrMonthChanged: function (year, month) {
        self.currY = year;
        self.currM = month;
        self.loadCal(year, month);
      }
    });
    self.initEvent();
  };
  this.init = function(){

  };

};

var cal = new CalModel();
cal.init();
$(document).ready(function () {
  cal.loadCal(cal.currY, cal.currM);
  $('#jy').modal({backdrop:'static',show:false});
//  $('#yl').modal({backdrop:'static',show:false});
});