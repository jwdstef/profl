/**
 * 自动按比例显示图片，按比例压缩图片显示
 * 
 * @param maxWidth
 * @param maxHeight
 * @param objImg
 * @return
 */

function AutoResizeImage(maxWidth, maxHeight, objImg) {

	var img = new Image();
	img.src = objImg.src;
	var hRatio;
	var wRatio;
	var Ratio = 1;
	var w = img.width;
	var h = img.height;
	wRatio = maxWidth / w;
	hRatio = maxHeight / h;
	if (maxWidth == 0 && maxHeight == 0) {
		Ratio = 1;
	} else if (maxWidth == 0) {//
		if (hRatio < 1)
			Ratio = hRatio;
	} else if (maxHeight == 0) {
		if (wRatio < 1)
			Ratio = wRatio;
	} else if (wRatio < 1 || hRatio < 1) {
		Ratio = (wRatio <= hRatio ? wRatio : hRatio);
	}
	if (Ratio < 1) {
		w = w * Ratio;
		h = h * Ratio;
	}
	objImg.height = h;
	objImg.width = w;
}
/**
 * 判断文件类型
 * 
 * @param fileName
 * @return
 */
function fileiIsImage(fileName) {
	var ext = fileName.substr(fileName.lastIndexOf(".") + 1)
	ext = ext.toLowerCase()
	if (!(ext == "bmp" || ext == "gif" || ext == "jpg")) {
		alert("类型不对")
		return -1
	}

}

function limitImg() {
	var img = document.getElementById(arguments[0]);// 显示图片的对象
	var maxSize = arguments[1];//
	var allowGIF = arguments[2] || false;
	var maxWidth = arguments[3] || 0;
	var maxHeight = arguments[4] || 0;
	var postfix = getPostfix(img.src);
	var str = ".jpg";
	if (allowGIF) {
		str += ".gif"
	}
	if (str.indexOf(postfix.toLowerCase()) == -1) {
		if (allowGIF) {
			return "图片格式不对，只能上传jpg或gif图像";
		} else {
			return "图片格式不对，只能上传jpg图像";
		}
	} else if (img.fileSize > maxSize * 1024) {
		return "图片大小超过限制,请限制在" + maxSize + "K以内";
	} else {
		if (img.fileSize == -1) {
			return "图片格式错误，可能是已经损坏或者更改扩展名导致，请重新选择一张图片";
		} else {
			if (maxWidth > 0) {
				if (img.width > maxWidth) {
					return "图片宽度超过限制，请保持在" + maxWidth + "像素内";
				} else {
					if (img.height > maxHeight) {
						return "图片高度超过限制，请保持在" + maxHeight + "像素内";
					} else {
						return "";
					}
				}

			} else {
				return "";
			}
		}
	}
}
// 根据路径获取文件扩展名
function getPostfix(path) {
	return path.substring(path.lastIndexOf("."), path.length);
}
$(function() {
	// 上传图片
	$("#btnUpload").click(function() {
		alert(ajaxFileUpload());
	});
});
function ajaxFileUpload() {
	// 开始上传文件时显示一个图片
	$("#wait_loading").ajaxStart(function() {
		$(this).show();
		// 文件上传完成将图片隐藏起来
	}).ajaxComplete(function() {
		$(this).hide();
	});
	var elementIds = [ "flag" ]; // flag为id、name属性名
	$.ajaxFileUpload({
		url : 'uploadAjax.htm',
		type : 'post',
		secureuri : false, // 一般设置为false
		fileElementId : 'file', // 上传文件的id、name属性名
		dataType : 'text', // 返回值类型，一般设置为json、application/json
		elementIds : elementIds, // 传递参数到服务器
		success : function(data, status) {
			alert(data);
		},
		error : function(data, status, e) {
			alert(e);
		}
	});
	// return false;
}