
(function($) {
	
	/*if(!document.addEventListener) {
		var ie9 = "http://172.17.10.53:8080/sdzkpt-admin/common/js/IE9.js";
		var script = document.createElement('script');
		script.src = ie9;
		script.type = 'text/javascript';
		document.getElementsByTagName('head')[0].appendChild(script);
	}*/

})(jQuery);

/**
 * 用于获取浏览器路径中的参数
 * 
 * @author wangbinbin
 * @date 2016/11/1
 */ 
(function($){
	$.getUrlParam = function(name)
	{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r!=null) return unescape(r[2]); return null;
	}
})(jQuery);

/**
 * 覆写jquery默认resize方法，使其可以监控页面元素大小的改变
 * @param $
 * @param h
 * @param c
 */
(function($,window,undefined){
  '$:nomunge'; 
  
  var elems = $([]),
    
    jq_resize = $.resize = $.extend( $.resize, {} ),
    
    timeout_id,
    
    str_setTimeout = 'setTimeout',
    str_resize = 'resize',
    str_data = str_resize + '-special-event',
    str_delay = 'delay',
    str_throttle = 'throttleWindow';
  
  
  jq_resize[ str_delay ] = 250;
  
  jq_resize[ str_throttle ] = true;
  
      
  $.event.special[ str_resize ] = {
    
    setup: function() {
      if ( !jq_resize[ str_throttle ] && this[ str_setTimeout ] ) { return false; }
      
      var elem = $(this);
      
      elems = elems.add( elem );
      
      $.data( this, str_data, { w: elem.width(), h: elem.height() } );
      
      if ( elems.length === 1 ) {
        loopy();
      }
    },
    
    teardown: function() {
      if ( !jq_resize[ str_throttle ] && this[ str_setTimeout ] ) { return false; }
      
      var elem = $(this);
      
      elems = elems.not( elem );
      
      elem.removeData( str_data );
      
      if ( !elems.length ) {
        clearTimeout( timeout_id );
      }
    },
    
    // Called every time a 'resize' event callback is bound per element (new in
    // jQuery 1.4).
    add: function( handleObj ) {
      // Since window has its own native 'resize' event, return false so that
      // jQuery doesn't modify the event object. Unless, of course, we're
      // throttling the 'resize' event for window.
      if ( !jq_resize[ str_throttle ] && this[ str_setTimeout ] ) { return false; }
      
      var old_handler;
      
      // The new_handler function is executed every time the event is triggered.
      // This is used to update the internal element data store with the width
      // and height when the event is triggered manually, to avoid double-firing
      // of the event callback. See the "Double firing issue in jQuery 1.3.2"
      // comments above for more information.
      
      function new_handler( e, w, h ) {
        var elem = $(this),
          data = $.data( this, str_data );
        
        // If called from the polling loop, w and h will be passed in as
        // arguments. If called manually, via .trigger( 'resize' ) or .resize(),
        // those values will need to be computed.
        data.w = w !== undefined ? w : elem.width();
        data.h = h !== undefined ? h : elem.height();
        
        old_handler.apply( this, arguments );
      };
      
      // This may seem a little complicated, but it normalizes the special event
      // .add method between jQuery 1.4/1.4.1 and 1.4.2+
      if ( $.isFunction( handleObj ) ) {
        // 1.4, 1.4.1
        old_handler = handleObj;
        return new_handler;
      } else {
        // 1.4.2+
        old_handler = handleObj.handler;
        handleObj.handler = new_handler;
      }
    }
    
  };
  
  function loopy() {
    
    // Start the polling loop, asynchronously.
    timeout_id = window[ str_setTimeout ](function(){
      
      // Iterate over all elements to which the 'resize' event is bound.
      elems.each(function(){
        var elem = $(this),
          width = elem.width(),
          height = elem.height(),
          data = $.data( this, str_data );
        
        // If element size has changed since the last time, update the element
        // data store and trigger the 'resize' event.
        if ( width !== data.w || height !== data.h ) {
          elem.trigger( str_resize, [ data.w = width, data.h = height ] );
        }
        
      });
      
      // Loop.
      loopy();
      
    }, jq_resize[ str_delay ] );
    
  };
  
})(jQuery,this);

var browserVersion = window.navigator.userAgent.toUpperCase();
var isOpera = browserVersion.indexOf("OPERA") > -1 ? true : false;
var isFireFox = browserVersion.indexOf("FIREFOX") > -1 ? true : false;
var isChrome = browserVersion.indexOf("CHROME") > -1 ? true : false;
var isSafari = browserVersion.indexOf("SAFARI") > -1 ? true : false;
var isIE = (!!window.ActiveXObject || "ActiveXObject" in window);
var isIE9More = (! -[1, ] == false);
function reinitIframe(left,iframeId, minHeight) {
    try {
        	var iframe = document.getElementById(iframeId);
            var bHeight = 0;
            if (isChrome == false && isSafari == false)
                bHeight = iframe.contentWindow.document.body.scrollHeight;

            var dHeight = 0;
            if (isFireFox == true)
                dHeight = iframe.contentWindow.document.documentElement.offsetHeight;
            else if (isIE == false && isOpera == false)
                dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
            else if (isIE == true && isIE9More) {//ie9+
                var heightDeviation = bHeight - eval("window.IE9MoreRealHeight" + iframeId);
                if (heightDeviation == 0) {
                    bHeight += 3;
                } else if (heightDeviation != 3) {
                    eval("window.IE9MoreRealHeight" + iframeId + "=" + bHeight);
                    bHeight += 3;
                }
            }
            else//ie[6-8]、OPERA
                bHeight += 3;

            var height = Math.max(bHeight, dHeight);
            if (height < minHeight) height = minHeight;
            
            var windowHeight = $(window).height();
            
            height = Math.max(height, (windowHeight-80));
            
            iframe.style.height = height-3 + "px";
            
            $(left).height(height);
        
    } catch (ex) { }
}
function resizeHtml(left,iframeId, minHeight) {
    eval("window.IE9MoreRealHeight" + iframeId + "=0");
    window.setInterval("reinitIframe('" + left + "','" + iframeId + "'," + minHeight + ")", 100);
    
    $("#contentFrame").width($(".contentView").width()-$(left).width() - .5);
}


/**
 * 自考平台后台采用frameset等方式布局，故定义此方法来监听浏览器及document元素宽高变化，以便显示滚动条
 * 
 * @author binbin
 * @date 2016/11/10
 */ 
(function($){
	
	// 监测document元素宽高变化
	$.fn.resizeDocument = function() {
		var _this = this;
		
		resizeHtml('.sideView','contentFrame',200);
		
		// 监听html元素的宽高改变
		$(_this).resize(function(){
			resizeHtml('.sideView','contentFrame',200);
		});
	}
	
})(jQuery);

/**
 * check当前登录会话是否过期，如果过期，跳转至登录界面
 * @author wangbinbin
 * @date 2016/11/22
 */
$.ajaxSetup({
	complete: function(xhr, status) {
		
		var sessionStatus = xhr.status;
		
		if(sessionStatus == '401') {
			
			var top = window;
			while(top != top.parent) {
				top = top.parent;
			}
			var curHref  = window.location.href;
			var isOp = curHref.indexOf("op-page");
			var appAddress = "";
			if(isOp == -1){
				appAddress = Constants.appAddress + "";
			}else{
				appAddress = Constants.appOpAddress + "op-page/"
			}
			top.layer.closeAll();
			top.layer.open({
				  type: 1,
				  title: false,
				  closeBtn: 0,
				  shadeClose: false,
				  content: '<div style="text-align:left;padding:10px 10px;font-size:14px;background:#fff;">登录会话已过期，将在 <span id="countDownSpan" class="countDownSpan" style="color:red"></span> 秒后自动跳转至登录页！<br> <small>如若长时间无跳转，请点击<a style="text-decoration: underline;color:#056dae;" target="_self" href="'+appAddress+'login.html'+'">立即跳转</a></small></div>',
				  success: function(layero, index){
					  countDown(layero,10); // 倒计时器
				  }
				});
			
		}
	}
});

/**
 * 倒计时函数
 * @param layero 弹出层对象
 * @param seconds 倒计时时间
 * 
 * @author wangbinbin 
 * @date 2016/11/22
 * 
 */
function countDown(layero,seconds) {
	
	$(layero).find('.countDownSpan').text(seconds)
	
	setTimeout(function(){
		seconds--;
		
		if(seconds>0) {
			countDown(layero, seconds);
		}
		
		if(seconds <=0 ) {
			
			var top = window;
			while(top != top.parent) {
				top = top.parent;
			}
			var curHref  = window.location.href;
			var isOp = curHref.indexOf("op-page");
			var appAddress = "";
			if(isOp == -1){
				appAddress = Constants.appAddress + "";
			}else{
				appAddress = Constants.appOpAddress + "op-page/"
			}
			
			top.location.href = appAddress+'login.html';
		}
	},1000);
}



var common={};
common.render = function(container,tpl){
	if(container&&tpl){
		$(container).html(tpl)
	}
}


/**
 * 适配浏览器滚动条问题
 * 
 * @author wangbinbin
 * @date 2016/11/22
 * 
 */
$(function(){
	
	// 监控页面元素高度变化
	$('html').resizeDocument();
	
});


/**
 * texarea截取字符
 * 
 * @author bgx
 * @date 2016/11/22
 * 
 */
function subString(obj){
	if(obj.getAttribute("maxlength")){
		var max_length = parseInt(obj.getAttribute("maxlength"));
		 if(obj.value.length > max_length){ 
			 obj.value=obj.value.substring(0,max_length);
		 }
	}
}

/**
 * excel 导出插件
 * @param $
 * 
 * @author yaojie
 * @date 2016/12/20
 */
(function($) {
	$.sdExport = function(opts) {
		
		var settings = {
			url: "", // 请求路径
			fileName: "export.xls", // excel导出默认名称
			tplUrl: "", // excel模板文件路径
			data: {},
			argument : {
				divId: "div_download",
				ifName: "iframe_download",
				formName: "form_download"
			}
		};
		
		var property = $.extend(true, {}, settings, opts);
		
		if (!(property.url && property.tplUrl && property.fileName)) {
			return;
		}
		
		createDiv(property);
	};
	
	/* 创建表单 */
	function createForm(property) {
		var form = '<form id="demoForm" target="' + property.argument.ifName + '" style="display:none" action="' + property.url +'" method="POST">' +
			'<input type="hidden" name="tplUrl" value="' + property.tplUrl + '">' +
			'<input type="hidden" name="fileName" value="' + property.fileName + '">';
			for (var i in property.data) {
				form += '<input type="hidden" name="' + i + '" value="' + property.data[i] + '">';
			}
			form += '</form>';
			$form = $(form);
		return $form;
	}

	/* 创建Iframe */
	function createIframe(property) {
		var $iframe = $('<iframe name="' + property.argument.ifName + '">');
		$iframe.on('load', function(event) {
			var errorMsg = $.trim($(this).contents().find("body").text());
			if (errorMsg && errorMsg.indexOf('Error') >= 0) {
				var jsonData = {
					type: "alert"
				};
				jsonData['msg'] = string_sb; //?
			}
		});
		return $iframe;
	}

	/* 创建Div */
	function createDiv(property) {
		var $div = $("#" + property.argument.divId);
		if ($div) {
			$div.empty();
		} else {
			$div = $('<div style="display:none" id="' + property.argument.divId + '">');
		}
		var $form = createForm(property);
		var $iframe = createIframe(property);
		$div.append($iframe);
		$div.append($form);
		$('body').append($div);
		$form.submit();
	}

})(jQuery);

/*获取光标位置*/  
function getCursorPos(obj)  
{   
    var CaretPos = 0;   
    // IE Support   
    if (document.selection) {   
        obj.focus (); //获取光标位置函数   
        var Sel = document.selection.createRange ();   
        Sel.moveStart ('character', -obj.value.length);  
        CaretPos = Sel.text.length;   
    }   
    // Firefox/Safari/Chrome/Opera support   
    else if (obj.selectionStart || obj.selectionStart == '0')   
        CaretPos = obj.selectionEnd;   
    return (CaretPos);   
}   
/*  
定位光标  
*/   
function setCursorPos(obj,pos)   
{   
    if(obj.setSelectionRange) { //Firefox/Safari/Chrome/Opera  
        obj.focus(); //  
        obj.setSelectionRange(pos,pos);   
    } else if (obj.createTextRange) { // IE  
        var range = obj.createTextRange();   
        range.collapse(true);   
        range.moveEnd('character', pos);   
        range.moveStart('character', pos);   
        range.select();   
    }   
}

/*  
替换后定位光标在原处,可以这样调用onkeyup=replaceAndSetPos(this,/[^/d]/g,'');  
*/   
function replaceAndSetPos(obj,pattern,text){   
  /*if(event.shiftKey||event.altKey||event.ctrlKey||event.keyCode==16||event.keyCode==17||event.keyCode==18||(event.shiftKey&&event.keyCode==36))  
   return; */  
    var pos=getCursorPos(obj);//保存原始光标位置   
    var temp=obj.value; //保存原始值   
    var max_length = obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : "";  
    if(pattern){
    	obj.value=temp.replace(pattern,text);//替换掉非法值   
    }
    if(max_length){
    	obj.value=obj.value.substring(0,max_length);
    }
    //截掉超过长度限制的字串（此方法要求已设定元素的maxlength属性值）  
    if(obj.value.length > max_length){  
        //法一：obj.value = obj.value.substring( 0,max_length);若用户在中间进行输入，此方法则达不到效果  
        //法二：可以满足任何情况（当超过输入了，去掉新输入的字符）  
        var str1 = obj.value.substring( 0,pos-1 );  
        var str2 = obj.value.substring( pos,max_length+1 );  
        obj.value = str1 + str2;  
        /*法三：在支持keycode等一系列的情况下使用 
        var e=e||event;  
        currKey=e.keyCode||e.which||e.charCode; 
        currKey = 0; 
        或 
        window.onkeydown=function(){  
            if( event.keyCode!=37 && event.keyCode!=39 && event.keyCode!=8 )// 左、右、删除 
                { return false; } 
        }*/  
    }  
    pos=pos-(temp.length-obj.value.length);//当前光标位置   
    setCursorPos(obj,pos);//设置光标
}
/**
 * 获取请求参数
 * @param {Object} parameter
 */
function getQueryMbWeb(parameter) {
	/*
	 * var str=document.URL; //取得整个地址栏 var num=str.indexOf("?");
	 * str=str.substr(num);
	 */
	var reg = new RegExp("(^|&)" + parameter + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}
/*
 * 获取默认图片，传入图片对象和默认图片路径
 * */
function getDefaultImg(obj, path){
	if(obj && obj.nodeName == "IMG"){
		obj.src = path;
	}
}

/**
 * 转换文本中的img标签的地址
 * @param context
 */
function convertImgUrlByUEditor(context) {
    var pattern = /<img[^>]+src=['"]([^'"]+)['"]+/g;
    var _srcArr = [];
    var temp;
    while ((temp = pattern.exec(context)) != null) {
    	if(temp[1].indexOf("http://")!=-1||temp[1].indexOf("https://")!=-1){
    		continue;
    	}
        _srcArr.push(temp[1]);
    }
    if (_srcArr.length > 0) {
        for (var j = 0; j < _srcArr.length; j++) {
            context = context.replace(_srcArr[j], Constants.manageFileAddress + _srcArr[j]);
        }
    }
    return context;
}

/**
 * 动态加载script
 */
function dynamicLoadScript(fn) {
	var configJs = $.getScript(Constants.ueditor.configJs);
	var allJs = $.getScript(Constants.ueditor.allJs);
	$.when(configJs, allJs).done(function(configData, allData) {
		if (configData[1] == "success" && allData[1] == "success") {
            eval(configData[0]);
            eval(allData[0]);
            if (typeof fn == "function") {
                fn();
            }
		}
	});
};