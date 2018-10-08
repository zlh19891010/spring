var id = getQueryMbWeb("id");
//页面列表初始化
  var ractive = new Ractive({
	  el: '#customtable',
		template: '#test-template',
		oninit: function(options) {
			var _this=this;
			$.sdAjax({
				url: Constants.ctrlAddress + "qptabledb/getRounds?id="+id,
				dataType: 'json',
				type: 'get',
				contentType: 'application/json',
				async: true, // 是否异步
				waitFlag: true, // 是否需要加载等待动画
				successCallback: function(data) {
					if(data && data.result) {
						_this.set("list",data.object);
						console.log(data.object)
					} else {
						success("操作失败");
					}

				}
			})
		},
		oncomplete:function(){
			var _this=this;
			
		}
  });

 

