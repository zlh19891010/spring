var ractive = new Ractive({
	el: '#cknrxx',
	template: '#nrxx-templete',
	oninit: function() {
		this.loadData();
	},
	loadData:function(){
		var _this=this;
		$.sdAjax({
		url: Constants.ctrlAddress + 'nrgl/selectNrxxById?id=' + store.get("nrxxId"),
		type: 'GET',
		async: true, // 是否异步
		waitFlag: true, // 是否需要加载等待动画
		successCallback: function(data) {
			if(data && data.result && data.object) {
			 var nrxx=data.object;
			 _this.set("nrxx",nrxx);
			 var flMap = nrxx.flxxMap;
			 var list = new Array();
			 for(var key in flMap){
			 	var json=eval('(' + key + ')');
			 	var flxx={};
			 	flxx.flsx=Constants.flxx[parseInt(json.flsx)];
			 	flxx.flmc=json.flmc;
			 	list.push(flxx);
			 }
			 _this.set("flxxSelect",list);
			  var elements = $(".hf-nrxx");
				     $.each(elements,function(){
				     var temp =  $(this).text().replace(/\n/g,'<br/>');
				     $(this).html(temp);
					 })
		  }
		}
	})
	}
	,
	oncomplete:function(){
		
	}
})