(function(){

//配置
var config = {
	'audio':{
		'icon':'audio-record-play',
		'text':true
	},
	'loading': 'loading-ic'
};
var audio = document.getElementById('music');

$('.u-globalAudio').bind("tap",function(){
    if(audio.paused){
        $('.u-globalAudio').attr('src','img/music_on.png');
        audio.play();
    }else{
        $('.u-globalAudio').attr('src','img/music_off.png');
        audio.pause();
    }
});
//loading
window.onload = function(){
	$('#loading').hide();
    $('.page').picLazyLoad({threshold: 100});
    $('#sub_1').addClass('fadeIn');
    $('#sub_1').removeClass('hide');
    audio.play();
};

var pageIndex = 1,
	pageTotal = $('.page').length,
	towards = { up:1, right:2, down:3, left:4},
	isAnimating = false;

//禁用手机默认的触屏滚动行为
document.addEventListener('touchmove',function(event){
	event.preventDefault(); },false);

$(document).swipeUp(function(){
	if (isAnimating) return;
	if (pageIndex < pageTotal) { 
		pageIndex+=1; 
	}else{
		pageIndex=1;
	};
	pageMove(towards.up);
})

$(document).swipeDown(function(){
	if (isAnimating) return;
	if (pageIndex > 1) { 
		pageIndex-=1; 
	}else{
		pageIndex=pageTotal;
	};
	pageMove(towards.down);	
})

function pageMove(tw){
	var lastPage;
    var lastIndex;
	if(tw=='1'){
		if(pageIndex==1){
			lastPage = ".page-"+pageTotal;
            lastIndex = pageTotal;
		}else{
			lastPage = ".page-"+(pageIndex-1);
            lastIndex = pageIndex - 1;
		}
		
	}else if(tw=='3'){
		if(pageIndex==pageTotal){
			lastPage = ".page-1";
            lastIndex = 1;
		}else{
			lastPage = ".page-"+(pageIndex+1);
            lastIndex = pageIndex + 1;
		}
		
	}

	var nowPage = ".page-"+pageIndex;
	
	switch(tw) {
		case towards.up:
			outClass = 'pt-page-moveToTop';
			inClass = 'pt-page-moveFromBottom';
			break;
		case towards.down:
			outClass = 'pt-page-moveToBottom';
			inClass = 'pt-page-moveFromTop';
			break;
	}
	isAnimating = true;
	$(nowPage).removeClass("hide");
	$(lastPage).addClass(outClass);
	$(nowPage).addClass(inClass);
	
	setTimeout(function(){
		$(lastPage).removeClass('page-current');
		$(lastPage).removeClass(outClass);
		$(lastPage).addClass("hide");
		$(lastPage).find("img").addClass("hide");

		$(nowPage).addClass('page-current');
		$(nowPage).removeClass(inClass);
		$(nowPage).find("img").removeClass("hide");

        $('#sub_'+lastIndex).removeClass('fadeIn');
        $('#sub_'+lastIndex).addClass("hide");

        $('#sub_'+pageIndex).addClass('fadeIn');
        $('#sub_'+pageIndex).removeClass('hide');
		isAnimating = false;
	},800);
}

})();