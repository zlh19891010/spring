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
			url: Constants.ctrlAddress + "qptabledb/rooms",
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
					colLabel: '房间ID',
					style: {
						textAlign: 'center',
						width: '200px',
					},
					colModel: {
						style: {
							textAlign: 'center',
						},
						innerHtml: function(data) {
							return '<span title="' + (data.roomId==null?"":data.roomId) +
								'">' + (data.roomId==null?"":data.roomId) +
								'</span>';
						}
					}
				},
				{
					colLabel: '拥有者',
					style: {
						textAlign: 'center',
						width: '200px',
					},
					colModel: {
						style: {
							textAlign: 'center',
						},
						innerHtml: function(data) {
							return '<span title="' + (data.owner==null?"":data.owner) +
								'">' + (data.owner==null?"":data.owner) +
								'</span>';
						}
					}
				},
				{
					colLabel: '玩家数',
					style: {
						textAlign: 'center',
						width: '200px',
					},
					colModel: {
						style: {
							textAlign: 'center',
						},
						innerHtml: function(data) {
							return '<span  title="' + (data.pCount==null?"":data.pCount) +
								'">' + (data.pCount==null?"":data.pCount) +
								'</span>';
						}
					}
				},
				{
					colLabel: '对局数',
					style: {
						textAlign: 'center',
						width: '200px',
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + (data.dCount==null?"":data.dCount) +
								'">' + (data.dCount==null?"":data.dCount) +
								'</span>';
						}
					}
				},
				{
					colLabel: '时间',
					style: {
						textAlign: 'center',
						width: '160px',
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + (data.time==null?"":new Date(data.time).format("yyyy-MM-dd hh:mm:ss")) +
								'">' +   (data.time==null?"":new Date(data.time).format("yyyy-MM-dd hh:mm:ss"))   +
								'</span>';
						}
					}
				},
				{
					colLabel: '操作',
					style: {
						textAlign: 'center',
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							var str = '<span class="look-icon" onclick = "ckxq(\'' + data.guid + '\');">查看详情</span>';
							str += '<span class="look-icon" onclick = "ckwj(\'' + data.guid + '\');">查看玩家</span>';
							return str;
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
  
function ckxq(id){
	var opts = {
			content: 'room/round.html?id='+id,
			scroll: false,
			title: '',
			type: 2,
			width: '78%',
			height: '63%',
			shadeClose: false,
			end: function() {
				
			}
		}
		dialog(opts);
}


function ckwj(id){
	var opts = {
			content: 'room/perpson.html?id='+id,
			scroll: true,
			title: '',
			type: 2,
			width: '78%',
			height: '63%',
			shadeClose: false,
			end: function() {
				
			}
		}
		dialog(opts);
}

