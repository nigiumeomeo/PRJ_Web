/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.intefaces;

import java.util.List;
import java.util.Map;
import model.Category;


public interface ICategoryDao extends GenericDao<Category> {
    Map<Integer, Integer> getQuantityOfCategory(List<Category> cList);
}
