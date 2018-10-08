var param ={
		current: 1,
		size: 10	
}  
  
//页面列表初始化
  var ractive = new Ractive({
	  el: '#test',
		template: '#test-template',
		oninit: function(options) {
			list(param);
		},
		oncomplete:function(){
			var _this=this;
			this.on({
				"cx":function(){
					var data=$.extend(param,_this.get("param"));
					list(data);
				}
			})
		}
  });

  function list(param){
	  $('#customtable').sdGrid({
			url: Constants.ctrlAddress + "tGlptGlyglGlyxx/selectStatistics",
			type: 'POST',
			data: param,
			async: true,
			waitFlag: true, // true：加载数据时显示等待动画， false：不显示加载动画。
			callback: function() {
				// 数据加载完成后，希望执行的操作
				$.sdCheckAll({
					//pName:'jhshall',
					//cName:'jhsh'
				})
			},
			colProperty: [{
					colLabel: '昵称',
					style: {
						textAlign: 'center',
						width: '250px'
					},
					colModel: {
						style: {
							textAlign: 'center',
						},
						innerHtml: function(data) {
							return '<span title="' + (data.name==null?"":data.name) +
								'">' +  (data.name==null?"":data.name)+
								'</span>';
						}
					}
				},
				{
					colLabel: '玩家数量',
					style: {
						textAlign: 'center',
						width: '250px'
					},
					colModel: {
						style: {
							textAlign: 'center',
						},
						innerHtml: function(data) {
							return '<span title="' + (data.pCount==null?0:data.pCount) +
								'">' + (data.pCount==null?0:data.pCount) +
								'</span>';
						}
					}
				},
				{
					colLabel: '充值总金额',
					style: {
						textAlign: 'center',
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + (data.pAmount==null?0:data.pAmount) +
								'">' + (data.pAmount==null?0:data.pAmount) +
								'</span>';
						}
					}
				}
			],
			pagination: { // 设置是否显示分页，默认显示分页部分。  show:true 显示分页， show:false 不显示分页
				show: true,
				pageSize: 10 // 可选，默认10条
			}
		});
  }
  
	

