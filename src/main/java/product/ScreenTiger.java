package product;

import java.util.ArrayList;
import java.util.List;

import model.Price;

public class ScreenTiger extends Product{

	public ScreenTiger() {
		this.setName("ScreenTiger 录屏工具");
		this.setVersion("Beta1.0");
		this.setDescription("专业便捷的录屏软件,可以帮助你快速创建像课本一样的视频教程。");
		List<Price> price = new ArrayList<Price>();
		price.add(new Price(1,0,100));
		price.add(new Price(6,0,500));
		price.add(new Price(12,0,1000));
		this.setPrice(price);
	}
}
