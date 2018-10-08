var jsId = "";
var id = getQueryMbWeb("id");
    	var ractive = new Ractive({
    		el: '#yhxx-form',
   			 template: '#yhxx-template',
   			 oninit: function(options) {
   			 	 var _this = this;
   			 	var data = {
   			 		jsgs:Constants.jsgsyy
   			 	};
   			 	$.sdAjax({
                     url:Constants.ctrlAddress+'getJsxxAll',
                     type:'POST',
                     async:false,
                     data:data,
                     contentType: 'application/json',
                     successCallback:function(data){
                     	_this.set("jsxxlist",data.object)
                     	var size =  Math.floor((data.object.length-1)/4) +1;
                     	var xhlist = [];
                     	for(var i = 1;i<size;i++){
                     		xhlist.push({ks:4*i,js:4*(i+1)});
                     	}
                     	_this.set("xhlist",xhlist)
                     }
 	              })
   			 	  _this.on("save",function(event){
   			 	  	 $("#form-yhxx").submit();
   			 	  });
   			 	  _this.on("fanhui",function(event){
   			 	  	 window.location.href="yhgl.html";
   			 	  });
   			 },
   			 oncomplete: function(){
   			 	var _this = this;
   			 	$.sdAjax({
		            url :Constants.ctrlAddress + "getYhxxbyId?id="+id,
		            dataType : 'json',
		            type : 'get',
		            contentType : 'application/json',
		            async : true, // 是否异步
		            waitFlag : true, // 是否需要加载等待动画
		            successCallback : function(data) {
		            	_this.set("yhxx",data.object)
		            	if (data.object.yhtx != null && data.object.yhtx != '') {
  		    				_this.set("yhtx",Constants.manageFileAddress + data.object.yhtx);
  		    			} else {
  		    				_this.set("yhtx",'');
  		    			}
		    			jsId = data.object.jsId
		    			var jsxx = _this.get("jsxxlist")
		    			for(var i = 0;i < jsxx.length;i++){
							if(jsxx[i].id == jsId){
								_this.set("jsmcs",jsxx[i].jsmc)
							}
						}
           		 	  }
        		})
   			 },
    	});
    	$(".hp-ckqx").click(function() {
			var opts = {
	    				content:'yhgl/ckqx.html?jsId='+jsId +'&jsgs=02',   
	    				scroll:true,  
	    				title:'查看权限',
	    				type:2,			
	    				width:'300px',
	    				height:'400px',
	    				shadeClose:false,
	    				end:function(){
	    				}
	    		}
    		dialog(opts);

		});
		function fanhui(){
			window.history.back()
	}
