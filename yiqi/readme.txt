https://www.jianshu.com/p/9d5bf0e4943f

还要找关于web的
https://github.com/bingganlen/springboot_fileinputdemo

https://github.com/bingganlen/ruoyi_study

文件路径https://blog.csdn.net/u012430402/article/details/79677601
//使用UUID给图片重命名，并去掉四个“-”
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		//获取文件的扩展名
		String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
		//设置图片上传路径
		String url = request.getSession().getServletContext().getRealPath("/upload");
		System.out.println(url);
		//以绝对路径保存重名命后的图片
		pictureFile.transferTo(new File(url+"/"+name + "." + ext));
		//把图片存储路径保存到数据库
		user.setImageURL("upload/"+name + "." + ext);
