var ractive = new Ractive({
	el: '#cknrxx',
	template: '#nrxx-templete',
	oninit: function() {
		this.loadData();
	},
	loadData:function(){
		var _this=this;
		$.sdAjax({
		url: Constants.ctrlAddress + 'nrsq/selectNrsqById?id=' + store.get("nrsqId"),
		type: 'GET',
		async: false, // 是否异步
		waitFlag: true, // 是否需要加载等待动画
		successCallback: function(data) {
			if(data && data.result && data.object) {
			 var nrsq=data.object;
			 _this.set("nrsq",nrsq);
			  var flMap = nrsq.flxxMap;
			 var list = new Array();
			 for(var key in flMap){
			 	var json=eval('(' + key + ')');
			 	var flxx={};
			 	flxx.flsx=Constants.flxx[parseInt(json.flsx)];
			 	flxx.flmc=json.flmc;
			 	list.push(flxx);
			 }
			 _this.set("flxxSelect",list);
		  }
		}
	})
	}
	,
	oncomplete:function(){
		
	}
})