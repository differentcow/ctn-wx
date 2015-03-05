(function ($) {
    $.fn.carcal = function (settings) {
        var curdate = new Date();
        var year = settings == null ? curdate.getFullYear() : (settings.year == null ? curdate.getFullYear() : settings.year);
        if (year < 1980 || year > 3000) {
            year = curdate.getFullYear();
        }
        var month = settings == null ? curdate.getMonth() : (settings.month == null ? curdate.getMonth() : settings.month);
        if (month < 1 || month > 12) {
            month = curdate.getMonth();
        }
        if (settings == null) {
            settings = {};
        }
        if (settings.yearOrMonthChanged == null) {
            settings.yearOrMonthChanged = function (iyear, imonth) {
                this.carcal({
                    year: iyear,
                    month: imonth,
                    dayClick: settings.dayClick
                });
            }
        }
        settings.year = year;
        settings.month = month;
        this.data("cal_curdata", settings);
        this.html('<div class="cal_t"><table><tbody><tr><td></td><td style="width:1rem;"><div class="cal_yearl"></div></td><td style="width:2rem;">' + settings.year + '</td><td style="width:1rem;">年</td><td style="width:1rem;"><div class="cal_yearr"></div></td><td></td><td style="width:1rem;"><div class="cal_monthl"></div></td><td style="width:0.5rem;">' + settings.month + '</td><td style="width:1rem;">月</td><td style="width:1rem;"><div class="cal_monthr"></div></td><td></td></tr></tbody></table></div><div class="cal_t_s"></div><div class="cal_w"><table><tr><td>星期一</td><td>星期二</td><td>星期三</td><td>星期四</td><td>星期五</td><td>星期六</td><td>星期天</td></tr></table></div><div class="cal_c"><table><tbody><tr><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td></tr><tr><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td></tr><tr><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td></tr><tr><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td></tr><tr><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td><td class="cal_d"></td></tr></tbody></table></div><div class="cal_b1"></div><div class="cal_b2"></div>');
        var date = new Date(settings.year, settings.month - 1, 1);
        var week = date.getDay();
        console.log("day of week -->"+week);
        var prev = 0;
        if (week == 0) {
            week = 7;
        }
        if (week == 1) {
            prev = 0;
        }
        else {
            prev = week - 1;
        }
        console.log("prev days -->"+prev);
        var startdate = new Date(date.valueOf() - prev * 24 * 60 * 60 * 1000);
        var calcon = this.find(".cal_c>table>tbody");
        var line = 0;
        var now = new Date();
        for (var i = 0; i < 35; i++) {
            var tempdate = new Date(startdate.valueOf() + i * 24 * 60 * 60 * 1000);
            var iweek = tempdate.getDay();
            if (iweek == 0) {
                iweek = 7;
            }
            iweek = iweek - 1;
//            var daycon = calcon.find(">tr:eq(" + line + ")>td:eq(" + iweek + ")");
           var daycon = $($(calcon.children('tr')[line]).children('td')[iweek]);
            if(tempdate.getFullYear()==now.getFullYear()&&tempdate.getMonth()==now.getMonth()&&tempdate.getDate()==now.getDate()) {
                daycon.addClass('cal_today');
            }
            if (tempdate.getFullYear() != settings.year || tempdate.getMonth() + 1 != settings.month) {
                daycon.html(tempdate.getDate());
                daycon.addClass("disabled");
            }
            else {
                var di = null;
                if (settings.data != null) {
                    for (var j = 0; j < settings.data.length; j++) {
                        var tdi = settings.data[j];
                        if (tdi.dateStr == tempdate.getFullYear() + "年" + (tempdate.getMonth() + 1) + "月" + tempdate.getDate()+'日') {
                            di = tdi;
                            break;
                        }
                    }
                }
                if (di == null) {
                    daycon.html(tempdate.getDate());
                    daycon.html('cal_curdata',{dateStr:tempdate.getFullYear() + "年" + (tempdate.getMonth() + 1) + "月" + tempdate.getDate()+'日',date:tempdate})
                }
                else {
                    daycon.html('<table><tbody><tr><td colspan="4">' + tempdate.getDate() + '</td></tr><tr><td colspan="4" class="km">' + di.mileage + 'KM</td></tr><tr><td>' + (di.xc ? '<img class="xc" />' : '&nbsp;') + '</td><td>' + (di.jy ? '<img class="jy" />' : '&nbsp;') + '</td><td>' + (di.by ? '<img class="by" />' : '&nbsp;') + '</td><td>' + (di.wz ? '<img class="wz" />' : '&nbsp;') + '</td></tr></tbody></table>');
                    daycon.data("cal_curdata", di);
                }
            }
            if (iweek == 6) {
                line++;
            }
        }
        var eleq = this;
        this.find(".cal_yearl").click(function () {
            var curset = eleq.data("cal_curdata");
            curset.yearOrMonthChanged(curset.year - 1, curset.month);
        });
        this.find(".cal_yearr").click(function () {
            var curset = eleq.data("cal_curdata");
            curset.yearOrMonthChanged(curset.year + 1, curset.month);
        });
        this.find(".cal_monthl").click(function () {
            var curset = eleq.data("cal_curdata");
            curset.yearOrMonthChanged(curset.month == 1 ? curset.year - 1 : curset.year, curset.month == 1 ? 12 : curset.month - 1);
        });
        this.find(".cal_monthr").click(function () {
            var curset = eleq.data("cal_curdata");
            curset.yearOrMonthChanged(curset.month == 12 ? curset.year + 1 : curset.year, curset.month == 12 ? 1 : curset.month + 1);
        });
        this.find(".cal_c>table>tbody>tr>td").click(function () {
            if ($(this).hasClass("disabled")) {
                return;
            }
            var curset = eleq.data("cal_curdata");
            if (curset.dayClick != null) {
                var curdata = $(this).data("cal_curdata");
                curset.dayClick(curdata);
            }
        })
    }
})(jQuery);