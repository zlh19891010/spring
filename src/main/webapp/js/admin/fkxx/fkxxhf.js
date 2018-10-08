var id = getQueryMbWeb("id");
var ractive = new Ractive({
	el: '#fkxxhf',
	template: '#test-template',
	oninit: function(options) {
		var _this = this;
		$.sdAjax({
			url: Constants.ctrlAddress + "getFkxxbyId?id="+id,
			type: "get",
			dataType: "json",
			contentType: "application/json",
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
					data.object.createTime = new Date(data.object.createTime).format("yyyy-MM-dd")
					data.object.hfsj = new Date(data.object.hfsj).format("yyyy-MM-dd")
					_this.set("fkxx",data.object)
					 var elements = $(".hf-xxfk-hfnr");
				     $.each(elements,function(){
				     var temp =  $(this).text().replace(/\n/g,'<br/>');
				     $(this).html(temp);
					 })
			}
		});
		_this.on("save", function(event) {
			$("#form-hffk").submit();
		});
		_this.on("cancel", function(event) {
			window.history.back();
		});
		
	},
	oncomplete: function() {
		var _this = this;
		$("#form-hffk").sdValidate({
			messageShow: { // 此属性用于设置提示信息的显示，及显示风格
				show: true,
				type: 4 // 1、正常显示、2、alert框方式显示 3、tip方式显示、4、吐司方式显示
			},
			focusout: true,
			action: function() {
				var datas = {
					id : id,					
					hfnr : _this.get("fkxx.hfnr")
				}
				$.sdAjax({
					url: Constants.ctrlAddress + 'updateFkxx',
					type: 'POST',
					async: true,
					contentType: 'application/json',
					data: datas,
					successCallback: function(data) {
						if(data.result) {
							success("回复成功", function() {
								window.history.back(-1);
							}, true)
						} 
					}
				});
			},
			rules: {
				hfnr: {
					required: true,
					maxlength:1000
				},
			},
			messages: { // 提示会有默认信息，如果你认为默认提示不好，或者不能满足业务提示需要，可以使用下面的属性覆盖
				hfnr: {
					required: '回复内容不能为空',
					maxlength:'最多输入1000字'
				},
			}
		});
	}
})