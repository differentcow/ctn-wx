/**
 * Created by TR on 2015/1/4.
 */
var OilRecData = [{year:'2014',mon:'01',logs:[{date:'01月01日',oil:'30.01',fee:200},{date:'01月10日',oil:'49.50',fee:300},{date:'01月18日',oil:33.01,fee:200},{date:'01月30日',oil:33.01,fee:200}]},
                  {year:'2014',mon:'02',logs:[{date:'02月10日',oil:'33.01',fee:200},{date:'02月17日',oil:'49.50',fee:300},{date:'02月20日',oil:33.01,fee:200},{date:'02月30日',oil:33.01,fee:200}]},
                  {year:'2014',mon:'03',logs:[{date:'03月01日',oil:'49.50',fee:300},{date:'03月10日',oil:'33.01',fee:200},{date:'03月17日',oil:33.01,fee:200},{date:'01月日',oil:33.01,fee:200}]},
                  {year:'2014',mon:'04',logs:[{date:'04月01日',oil:'33.01',fee:200},{date:'04月10日',oil:'33.10',fee:200},{date:'04月18日',oil:33.01,fee:200},{date:'04月30日',oil:33.01,fee:200}]},
                  {year:'2014',mon:'05',logs:[{date:'05月01日',oil:'49.05',fee:300},{date:'05月10日',oil:'49.50',fee:300},{date:'05月18日',oil:33.01,fee:200},{date:'05月30日',oil:33.01,fee:200}]},
                  {year:'2014',mon:'06',logs:[{date:'06月01日',oil:'33.01',fee:200},{date:'06月10日',oil:'33.01',fee:200},{date:'06月18日',oil:33.01,fee:200},{date:'06月30日',oil:33.01,fee:200}]},
                  {year:'2014',mon:'07',logs:[{date:'07月01日',oil:'49.50',fee:300},{date:'07月10日',oil:'49.50',fee:300},{date:'07月18日',oil:33.01,fee:200},{date:'07月30日',oil:33.01,fee:200}]},
                  {year:'2014',mon:'08',logs:[{date:'08月01日',oil:'30.01',fee:200},{date:'08月10日',oil:'49.50',fee:300},{date:'08月18日',oil:33.01,fee:200},{date:'08月30日',oil:33.01,fee:200}]},
                  {year:'2014',mon:'09',logs:[{date:'09月01日',oil:'30.01',fee:200},{date:'09月10日',oil:'49.50',fee:300},{date:'09月18日',oil:33.01,fee:200},{date:'09月30日',oil:33.01,fee:200}]},
                  {year:'2014',mon:'10',logs:[{date:'10月01日',oil:'30.01',fee:200},{date:'10月10日',oil:'49.50',fee:300},{date:'10月18日',oil:33.01,fee:200},{date:'10月30日',oil:33.01,fee:200}]},
                  {year:'2014',mon:'11',logs:[{date:'11月01日',oil:'30.01',fee:200},{date:'11月10日',oil:'49.50',fee:300},{date:'11月18日',oil:33.01,fee:200},{date:'11月30日',oil:33.01,fee:200}]},
                  {year:'2014',mon:'12',logs:[{date:'12月01日',oil:'30.01',fee:200},{date:'12月10日',oil:'49.50',fee:300},{date:'12月18日',oil:33.01,fee:200},{date:'12月30日',oil:33.01,fee:200}]},
                  {year:'2015',mon:'01',logs:[{date:'01月01日',oil:'30.01',fee:200},{date:'01月10日',oil:'49.50',fee:300},{date:'01月18日',oil:33.01,fee:200},{date:'01月30日',oil:33.01,fee:200}]},
                  {year:'2015',mon:'02',logs:[{date:'02月01日',oil:'30.01',fee:200},{date:'02月10日',oil:'49.50',fee:300},{date:'02月18日',oil:33.01,fee:200},{date:'02月30日',oil:33.01,fee:200}]},
                  {year:'2015',mon:'03',logs:[{date:'03月01日',oil:'30.01',fee:200},{date:'03月10日',oil:'49.50',fee:300},{date:'03月18日',oil:33.01,fee:200},{date:'03月30日',oil:33.01,fee:200}]}
];
var OilRecModel = function () {
    var self = this;
    this.init = function () {
        self.initEvent();
    };
    this.initEvent = function () {
        $('.nav-bar').tap(function () {
            var $this = $(this);
            var type = 20;
            switch(this.id){
                case 'all':
                    type=20;
                    break;
                case 'six':
                    type=6;
                    break;
                case 'one':
                    type=1
                    break;
            }
            self.loadData(type);
            $('.nav-active').removeClass('nav-active');
            $this.addClass('nav-active');
        });

        self.loadData(6);

    };
    this.initDetailEvent = function ($dom) {
        $dom.tap(function () {
            var $this = $(this);
            $this.addClass('detail-a');
            $this.find('img').attr('src','image/oilrec/jt_b.png');
            $this.next('div').addClass('detail-a');
            $this.tap(function () {
                var $this = $(this);
                $this.removeClass('detail-a');
                $this.find('img').attr('src','image/oilrec/jt_r.png');
                $this.next('div').removeClass('detail-a');
                self.initDetailEvent($this);
            });
        });
    };
    this.loadData = function(type){
        var now = new Date();
        var year =now.getFullYear();
        var mon = now.getMonth();
        if(mon==0){
            mon=12;
            year = year-1;
        }
        var fee = 0;
        var oil = 0;
        var count = 0;
        var html = '';
        for(var i=OilRecData.length-1;i>=0;i--){
            var data = OilRecData[i];
            if(parseInt(data.mon)==mon&&parseInt(data.year)==year){
               count = type;
            }
            if(count>0) {
                html+='<div class="detail">'
                +'<div class="detail-t"><img src="image/oilrec/jt_r.png" width="15px" height="15px">&nbsp;&nbsp;<span class="detail-d">'+data.year+'</span>年<span class="detail-d">'+data.mon+'</span>月</div>'
                +'<div class="detail-c container">';
                for (var j = 0; j < data.logs.length; j++) {
                    fee += parseFloat(data.logs[j].fee);
                    oil += parseFloat(data.logs[j].oil);
                    html+='<div class="row">'
                            +'<div class="col-xs-4">'
                            +data.logs[j].date
                            +'</div>'
                            +'<div class="col-xs-4">'
                            +'加油'+data.logs[j].oil+'升'
                            +'</div>'
                            +'<div class="col-xs-4">'
                            +'费用'+data.logs[j].fee+'元'
                            +'</div>'
                        +'</div>';
                        if(j<data.logs.length-1) {
                           html+='<div class="row detail-fg"></div>';
                        }


                }
                html+='</div> </div > ';
                count--;
            }


        }
        var tm= Math.round((fee/0.7)*100)/100;
        var afee = 70;
        var aoc = Math.round((oil/tm)*10000)/100;
        $('#afee').html(afee);
        $('#aoc').html(aoc);
        $('#fee').html(Math.round(fee*100)/100);
        $('#tm').html(tm);
        $('#detail-list').html(html);
        self.initDetailEvent($('.detail-t').not('detail-a'));

    }
};

var oilRec = new OilRecModel();
$(function() {
    oilRec.init();
});