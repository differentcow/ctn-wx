
$(document).ready(function() {
	var now = new Date();
	Load(now.getFullYear(), now.getMonth() + 1);
});
var preBykm = 0;

function Load(iyear, imonth) {
var datas = [];
	var wzCount = 2;
	var xcCount = 4;
	var preXCDay = 0;
	var xcInterval = 5;
	var preKm = 0;
	var jyInterval = 300;
	var byInterval = 5000;
	for (var i = 0; i < 31; i++) {
		var data = {
			date: iyear + "-" + imonth + "-" + (i + 1), //日期
			mileage: Math.round(Math.random() * 100), //里程
			wz: Math.round(Math.random() * 5) == 1, //违章
			xc: Math.round(Math.random() * 5) == 1, //洗车
			by: Math.round(Math.random() * 5) == 1, //保养
			jy: Math.round(Math.random() * 5) == 1, //加油
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
		yearOrMonthChanged: function(year, month) {
			Load(year, month);
		},
		dayClick: function(data) {
			alert(data == null ? "null" : data.date);
		}
	});
}