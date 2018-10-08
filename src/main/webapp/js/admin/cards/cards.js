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
			url: Constants.ctrlAddress + "tGlptCards/selectCards",
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
			colProperty: [
			              {
					colLabel: '发送者名称',
					style: {
						textAlign: 'left',
						width: '25%'
					},
					colModel: {
						style: {
							textAlign: 'left',
						},
						innerHtml: function(data) {
							return '<span title="' + (data.yhmc=="null"?"":data.yhmc) +
								'">' + (data.yhmc=="null"?"":data.yhmc) +
								'</span>';
						}
					}
				},{
					colLabel: '接收者昵称',
					style: {
						textAlign: 'left',
						width: '25%'
					},
					colModel: {
						style: {
							textAlign: 'left',
						},
						innerHtml: function(data) {
							return '<span title="' + (data.strNickName=="null"?"":data.strNickName) +
								'">' + (data.strNickName=="null"?"":data.strNickName) +
								'</span>';
						}
					}
				},
				{
					colLabel: '用户ID',
					style: {
						textAlign: 'left',
						width: '25%'
					},
					colModel: {
						style: {
							textAlign: 'left',
						},
						innerHtml: function(data) {
							return '<span title="' + (data.strAccounts==null?"":data.strAccounts) +
								'">' + (data.strAccounts==null?"":data.strAccounts) +
								'</span>';
						}
					}
				},
				{
					colLabel: '已发点卡数量',
					style: {
						textAlign: 'left',
						width: '25%'
					},
					colModel: {
						style: {
							textAlign: 'left',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' +(data.card==null?0:data.card) +
								'">' + (data.card==null?0:data.card) +
								'</span>';
						}
					}
				},
				{
					colLabel: '发送时间',
					style: {
						textAlign: 'center'
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + (data.createTime==null?"":new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss")) +
								'">' + (data.createTime==null?"":new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss"))   +
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
  
	

