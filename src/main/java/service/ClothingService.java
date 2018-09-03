package service;

import clothing.vo.Clothing;
import clothing.dao.ClothingMapper;
import clothing.vo.ClothingExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 新增服装信息
 * 根据价格删除服装信息
 * 根据价格更新服装数量
 * 查询所有服装信息
 * 根据价格查询服装信息
 */
@Service
public class ClothingService {

    @Resource
    private ClothingMapper clothingMapper;

    public List<Clothing> getAllClothingForString(String clause) {

        ClothingExample clothingExample = new ClothingExample();
        if (clause != null && !clause.equals("")) {
            clothingExample.setOrderByClause(clause);
        }
        return clothingMapper.selectByExample(clothingExample);
    }

    public Clothing getClothingByPrice(int price) {

        ClothingExample clothingExample = new ClothingExample();
        clothingExample.createCriteria().andPriceEqualTo(price);
        return clothingMapper.selectByExample(clothingExample).get(0);
    }

    public void insertClothing(Clothing clothing) {
        clothingMapper.insertSelective(clothing);
    }

    public void deleteClothingByPrice(int price) {
        ClothingExample clothingExample = new ClothingExample();
        ClothingExample.Criteria criteria = clothingExample.createCriteria();
        criteria.andPriceEqualTo(price);
        clothingMapper.deleteByExample(clothingExample);
    }

    public void updateClothingNumberByPrice(int increment, int price) {
        Clothing clothing = getClothingByPrice(price);
        int number = 0;
        if (clothing.getNumber() != null) {
            number = clothing.getNumber();
        }
        clothing.setNumber(number + increment);
        clothing.setUpdateTime(new Date());
        ClothingExample example = new ClothingExample();
        example.createCriteria().andPriceEqualTo(price);
        clothingMapper.updateByExampleSelective(clothing, example);
    }
}
