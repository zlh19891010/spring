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
			url: Constants.ctrlAddress + "orderInfo/selectOrders",
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
				colLabel: '用户ID',
				style: {
					textAlign: 'center',
					width: '200px'
				},
				colModel: {
					style: {
						textAlign: 'center',
					},
					innerHtml: function(data) {
						return '<span title="' + (data.gameUserId==null?"":data.gameUserId) +
							'">' +  (data.gameUserId==null?"":data.gameUserId) +
							'</span>';
					}
				}
			},
			{
				colLabel: '商品ID',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'center',
					},
					innerHtml: function(data) {
						return '<span title="' + (data.productId==null?"":data.productId) +
							'">' + (data.productId==null?"":data.productId) +
							'</span>';
					}
				}
			},
			{
				colLabel: '房卡数量',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'center',
					},
					innerHtml: function(data) {
						return '<span title="' + (data.productNum==null?0:data.productNum) +
							'">' + (data.productNum==null?0:data.productNum) +
							'</span>';
					}
				}
			},
			              {
					colLabel: 'AnySDK订单号',
					style: {
						textAlign: 'center',
						width: '200px'
					},
					colModel: {
						style: {
							textAlign: 'center',
						},
						innerHtml: function(data) {
							return '<span title="' + (data.orderId==null?"":data.orderId) +
								'">' +  (data.orderId==null?"":data.orderId) +
								'</span>';
						}
					}
				},
				{
					colLabel: '游戏订单号',
					style: {
						textAlign: 'center',
						width: '200px'
					},
					colModel: {
						style: {
							textAlign: 'center',
						},
						innerHtml: function(data) {
							return '<span title="' + (data.privateData==null?"":data.privateData) +
								'">' + (data.privateData==null?"":data.privateData) +
								'</span>';
						}
					}
				},
				{
					colLabel: '支付状态',
					style: {
						textAlign: 'center',
						width: '100px'
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							var str="";
							if(Constants.success==data.payStatus){
								str="<span style='color:green'>成功</span>";
							}else{
								str="<span style='color:red'>失败</span>";
							}
							
							return str;
						}
					}
				},{
					colLabel: '支付金额',
					style: {
						textAlign: 'center',
						width: '100px'
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							
							return '<span title="' + (data.amount==null?"":data.amount) +
							'">' + (data.amount==null?"":data.amount) +
							'</span>';;
						}
					}
				},
				{
					colLabel: '支付时间',
					style: {
						textAlign: 'center'
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' +(data.payTime==null?"": new Date(data.payTime).format("yyyy-MM-dd hh:mm:ss")) +
								'">' +  (data.payTime==null?"": new Date(data.payTime).format("yyyy-MM-dd hh:mm:ss"))   +
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
  
	

