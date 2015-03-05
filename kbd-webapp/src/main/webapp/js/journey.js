/**
 * Created by Zale on 2014/12/29.
 */
var JourneyDatas = [
    {
        toc: '10L',
        tm: '87km',
        aoc: '7.5L',
        route: [
            {time: '8:12', place: '五一广场新世纪百货', mileage: '12km', oc: '1.5L'},
            {time: '9:20', place: '开福寺南门', mileage: '50km', oc: '5L'},
            {time: '18:12', place: '芙蓉广场', mileage: '20km', oc: '3L'},
            {time: '20:00', place: '万达广场', mileage: '5km', oc: '0.5L'}
        ]
    },
    {
        toc: '10.8L',
        tm: '65km',
        aoc: '7.6L',
        route: [
            {time: '8:12', place: '步步高广场', mileage: '30km', oc: '5.1L'},
            {time: '9:20', place: '麓谷企业广场', mileage: '10km', oc: '1.2L'},
            {time: '20:00', place: '万达广场', mileage: '25km', oc: '4.5L'}
        ]
    },
    {
        toc: '6.0L',
        tm: '42km',
        aoc: '7.4L',
        route: [
            {time: '8:12', place: '世界之窗', mileage: '12km', oc: '1.5L'},
            {time: '9:20', place: '烈士公园', mileage: '20km', oc: '3L'},
            {time: '18:12', place: '芙蓉广场', mileage: '10km', oc: '1.5L'}
        ]}
];
var JourneyModel = function () {
    var self = this;
    this.getQueryString = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURIComponent(r[2]);
        return null;
    };
    this.formatDateTime = function (date, format) {
        if (!format || format == '')
            format = 'yyyy-MM-dd hh:mm:ss';
        if (typeof date === 'string' || typeof date === 'number')
            date = new Date(date);
        var year = date.getFullYear()
        var month = date.getMonth() + 1;
        var days = date.getDate();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();
        var res = format.match(/([yY]+)/);
        if (res) format = format.replace(res[0], year.toString().substr(res[0].length - 4, 4 - (res[0].length - 4)));
        res = format.match(/(M+)/);
        if (res) format = format.replace(res[0], (res[0].length == 2 && month < 10 ? '0' + month : month));
        res = format.match(/([dD]+)/);
        if (res) format = format.replace(res[0], (res[0].length == 2 && days < 10 ? '0' + days : days));
        res = format.match(/([hH]+)/);
        if (res) format = format.replace(res[0], (res[0].length == 2 && hours < 10 ? '0' + hours : hours));
        res = format.match(/([m]+)/);
        if (res) format = format.replace(res[0], (res[0].length == 2 && minutes < 10 ? '0' + minutes : minutes));
        res = format.match(/([Ss]+)/);
        if (res) format = format.replace(res[0], (res[0].length == 2 && seconds < 10 ? '0' + seconds : seconds));
        return format;
    };

    this.date = new Date(parseFloat(self.getQueryString('date')));
    this.init = function () {
        if (self.date == null) {
            self.date = new Date();
        }
        console.log(self.formatDateTime(self.date, 'yyyy-MM-dd'));
        $('.mid-content').html(self.formatDateTime(self.date, 'yyyy-MM-dd'));
        self.loadData();
        $('.left-btn').tap(function () {
            console.log('你正在点击左边')
            self.changeDay(-1);
        });
        $('.right-btn').tap(function () {
            self.changeDay(1);
        });
    };
    this.changeDay = function (day) {
        self.date.setDate(self.date.getDate() + day);
        $('.mid-content').html(self.formatDateTime(self.date, 'yyyy-MM-dd'));
        self.loadData();
    };

    this.loadData = function () {
        var num = Math.floor(Math.random() * ( 100 + 1))
        var data = JourneyDatas[num % 3];
        $('#toc').html(data.toc);
        $('#tm').html(data.tm);
        $('#aoc').html(data.aoc);
        var chtml = '';
        for (var i = 0; i < data.route.length; i++) {
            if (i == 0) {
                chtml += '<div class="row content"><div class="detail-b detail-top"><div class="col-xs-3 left"></div><div class="col-xs-2 center"></div><div class="col-xs-7 right"></div></div><div class="detail detail-top"><div class="col-xs-3 left">' + data.route[i].time + '</div><div class="col-xs-2 center"><span class="text">' + data.route[i].place.substr(0, 1) + '</span></div><div class="col-xs-7 right"><div class="text">' + data.route[i].place + '</div></div></div><div class="detail-a"><div class="col-xs-3 left"></div><div class="col-xs-2 center"></div><div class="col-xs-7 right">当次行程:' + data.route[i].mileage + '<br>当次油耗:' + data.route[i].oc + '</div></div>';
            } else if (i == data.route.length - 1) {
                chtml += '</div><div class="row bottom">'
                    + '<div class="detail-b">'
                    + '<div class="col-xs-3 left"></div>'
                    + '<div class="col-xs-2 center"></div>'
                    + '<div class="col-xs-7 right"></div>'
                    + '</div>'
                    + '<div class="detail">'
                    + '<div class="col-xs-3 left">'+data.route[i].time+'</div>'
                    + '<div class="col-xs-2 center"><span class="text">'+data.route[i].place.substr(0,1)+'</span></div>'
                    + '<div class="col-xs-7 right">'
                    + '<div class="text">'+data.route[i].place+'</div>'
                    + '</div>'
                    + '</div>'
                    + '<div class="detail-a">'
                    + '<div class="col-xs-3 left"></div>'
                    + '<div class="col-xs-2 center"></div>'
                    + '<div class="col-xs-7 right">当次行程:'+data.route[i].mileage+'<br>当次油耗:'+data.route[i].oc+'</div>'
                    + '</div>';
            } else {
                chtml += '<div class="detail-b">'
                    + '<div class="col-xs-3 left"></div>'
                    + '<div class="col-xs-2 center"></div>'
                    + '<div class="col-xs-7 right"></div>'
                    + '</div>'
                    + '<div class="detail">'
                    + '<div class="col-xs-3 left">'+data.route[i].time+'</div>'
                    + '<div class="col-xs-2 center"><span class="text">'+data.route[i].place.substr(0,1)+'</span></div>'
                    + '<div class="col-xs-7 right">'
                    + '<div class="text">'+data.route[i].place+'</div>'
                    + '</div>'
                    + '</div>'
                    + '<div class="detail-a">'
                    + '<div class="col-xs-3 left"></div>'
                    + '<div class="col-xs-2 center"></div>'
                    + '<div class="col-xs-7 right">当次行程:'+data.route[i].mileage+'<br>当次油耗:'+data.route[i].oc+'</div>'
                    + '</div>';
            }

        }
        $('#route').html(chtml);
    }

};
var journey = new JourneyModel();

$(function () {
    journey.init();
});