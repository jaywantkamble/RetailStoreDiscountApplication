//package com.retailstorediscount;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.retailstorediscount.model.Bill;
//import com.retailstorediscount.model.Item;
//import com.retailstorediscount.model.User;
//import com.retailstorediscount.model.UserType;
//
//@SpringBootTest
//public class DiscountUtilsTest {
//
////    @Test
////    public void testCalculatePercentageDiscountForEmployee() {
////        User user = new User(UserType.EMPLOYEE, LocalDate.now());
////        Item item1 = new Item("Item1", 100, false);
////        Item item2 = new Item("Item2", 200, true);
////        Bill bill = new Bill(user, Arrays.asList(item1, item2));
////
////        double discount = DiscountUtils.calculatePercentageDiscount(user, bill);
////        assertEquals(30, discount, 0.01);
////    }
////
////    @Test
////    public void testCalculatePercentageDiscountForAffiliate() {
////        User user = new User(UserType.AFFILIATE, LocalDate.now());
////        Item item1 = new Item("Item1", 100, false);
////        Item item2 = new Item("Item2", 200, true);
////        Bill bill = new Bill(user, Arrays.asList(item1, item2));
////
////        double discount = DiscountUtils.calculatePercentageDiscount(user, bill);
////        assertEquals(10, discount, 0.01);
////    }
////
////    @Test
////    public void testCalculatePercentageDiscountForCustomer() {
////        User user = new User(UserType.CUSTOMER, LocalDate.now().minusYears(3));
////        Item item1 = new Item("Item1", 100, false);
////        Item item2 = new Item("Item2", 200, true);
////        Bill bill = new Bill(user, Arrays.asList(item1, item2));
////
////        double discount = DiscountUtils.calculatePercentageDiscount(user, bill);
////        assertEquals(5, discount, 0.01);
////    }
//}
