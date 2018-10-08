var ractive = new Ractive({
	el: '#fjlbnr',
	template: '#fjlb',
	oninit: function() {
		this.loadData();
	},
	loadData:function(){
		var _this=this;
		var data={
			ids:store.get("lsfjIds")
		}
		$.sdAjax({
		url: Constants.ctrlAddress + 'fjgl/getFjList',
		type: 'POST',
		data:data,
		async: false, // 是否异步
		waitFlag: true, // 是否需要加载等待动画
		successCallback: function(data) {
			if(data && data.result && data.data.length>0) {
			 _this.set("list",data.data);
		  }
		}
	})
	}
	,
	oncomplete:function(){
		$.sdCheckAll({
					pName: 'bklball',
					cName: 'bklb'
				})
	}
})

//确定按钮
function qd(){
    var lsfjList=store.get("lsfjlb");
	var fjlb= new Array();
	$("input[name='bklb']:checked").each(function(index,value) {
		var fjxx = {
			id:value.id,
			ljm:value.getAttribute("ljm"),
			fjdz:value.getAttribute("fjdz"),
			fjdx:value.getAttribute("fjdx"),
			mtlx:value.getAttribute("mtlx"),
			wjgs:value.getAttribute("wjgs")
		}
			fjlb.push(fjxx);
	});
	store.set("lsfjlb",fjlb);
	closeParent();
}


function cxbtn(){
	var data={
			ljm:$("#cx_fjname").val().trim()
		}
	$.sdAjax({
		url: Constants.ctrlAddress + 'fjgl/getFjList',
		type: 'POST',
		data:data,
		async: false, // 是否异步
		waitFlag: true, // 是否需要加载等待动画
		successCallback: function(data) {
			if(data && data.result ) {
			 ractive.set("list",data.data);
		     }
		}
	})
}
