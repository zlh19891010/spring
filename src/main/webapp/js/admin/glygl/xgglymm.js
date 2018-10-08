var flag = getQueryMbWeb("flag");	
var ractive = new Ractive({
    		el: '#gly-form',
   			 template: '#gly-template',
   			 oninit: function(options) {
   			 	 var _this = this;
   			 	  _this.on("bc",function(event){
   			 	  	 $("#form-gly").submit();
   			 	  });
   			 	  _this.on("fh",function(event){
   			 	  	 closeParent();
   			 	  });
   			 	   _this.on("checkmm",function(event){
   			 	  	 if(_this.get("gly.dlmm")!=_this.get("gly.mm")){
   			 	  	 	message("两次输入密码不一致");
   			 	  	 }
   			 	  });
			     
   			 },
   			 oncomplete: function(){
   			 	var _this = this;
   			 	$("#form-gly").sdValidate({
		 			messageShow:{     // 此属性用于设置提示信息的显示，及显示风格
                         show:true,
                         type:4        // 1、正常显示、2、alert框方式显示 3、tip方式显示、4、吐司方式显示
                     },
                    focusout:true,
                    action: function(){
                    	var gly = _this.get("gly");
                    	gly.jsId = $("input[name='js']:checked").val();
                    	if(gly.dlmm!=gly.mm){
                    	    message("两次输入密码不一致");
                    	    return;
                    	}
                    	gly.id = getQueryMbWeb("id");
                    	_this.bc(gly);
                    },
                    rules : {
    					dlmm: {
    						required : true,
    						minlength:6
    					},
    					qrmm: {
    						required : true
    					},
    				},
    				messages : { // 提示会有默认信息，如果你认为默认提示不好，或者不能满足业务提示需要，可以使用下面的属性覆盖
    					dlmm: {
    						required : '密码不能为空',
    						minlength : '密码至少六位'
    					},
    					qrmm: {
    						required : '确认密码不能为空'
    					},

                    }
		 		});
   			 },
   			 //保存操作
   			 bc: function(data){
   			 	$.sdAjax({
			    		url:Constants.ctrlAddress + "tGlptGlyglGlyxx/modifyGly",
			    		type:'post',
			    		data:data,
			    		dataType:'json',
			    		successCallback:function(data){
			    			if(data.result){
			    				success("保存成功",function(){
			    					if(flag=="main"){
			    						window.parent.logOut();
			    					}
                           	    	closeParent();
                           	    },true)
			    				
			    			}
			    		}
			     });
   			 },
    	});
		function onk(event){
       			replaceAndSetPos(event,/^[\u4e00-\u9fa5]{0,}$/g,'');
       			replaceAndSetPos(event,/^(^\s+)|(\s+$)/g, '');
        	}