//package com.retailstorediscount;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.retailstorediscount.model.Bill;
//import com.retailstorediscount.model.Item;
//import com.retailstorediscount.model.User;
//import com.retailstorediscount.model.UserType;
//import com.retailstorediscount.service.DiscountStrategy;
//import com.retailstorediscount.service.impl.DiscountServiceImpl;
//import com.retailstorediscount.service.impl.DiscountStrategyFactory;
//
//@ExtendWith(MockitoExtension.class)
//public class DiscountServiceTestWithMockito {
//
//    @Mock
//    private DiscountStrategyFactory discountStrategyFactory;
//
//    @Mock
//    private DiscountStrategy discountStrategy;
//
//    @InjectMocks
//    private DiscountServiceImpl discountService;
//
//    private Bill bill;
//
//    @BeforeEach
//    public void setUp() {
//        User user = new User("John", UserType.EMPLOYEE, LocalDate.now());
//        List<Item> items = Arrays.asList(
//                new Item("Item1", 200, false),
//                new Item("Item2", 100, true),
//                new Item("Item3", 300, false)
//        );
//        bill = new Bill(user, items);
//    }
//
//    @Test
//    public void testEmployeeDiscount() {
//        when(discountStrategyFactory.getDiscountStrategy(UserType.EMPLOYEE)).thenReturn(discountStrategy);
//        when(discountStrategy.applyDiscount(bill)).thenReturn(150.0);
//
//        double netAmount = discountService.calculateNetPayableAmount(bill);
//
//        // Total amount: 600, percentage discount: 150 (mocked), flat discount: 30 (5*6)
//        assertEquals(420, netAmount, 0.01);
//    }
//
//    @Test
//    public void testAffiliateDiscount() {
//        User user = new User("John",UserType.AFFILIATE, LocalDate.now());
//        List<Item> items = Arrays.asList(
//                new Item("Item1", 150, false),
//                new Item("Item2", 150, true),
//                new Item("Item3", 300, false)
//        );
//        Bill bill = new Bill(user, items);
//
//        when(discountStrategyFactory.getDiscountStrategy(UserType.AFFILIATE)).thenReturn(discountStrategy);
//        when(discountStrategy.applyDiscount(bill)).thenReturn(45.0);
//
//        double netAmount = discountService.calculateNetPayableAmount(bill);
//
//        // Total amount: 600, percentage discount: 45 (mocked), flat discount: 30 (5*6)
//        assertEquals(525, netAmount, 0.01);
//    }
//
//    @Test
//    public void testCustomerDiscount() {
//        User user = new User("John",UserType.CUSTOMER, LocalDate.now().minusYears(3));
//        List<Item> items = Arrays.asList(
//                new Item("Item1", 100, false),
//                new Item("Item2", 100, true),
//                new Item("Item3", 200, false)
//        );
//        Bill bill = new Bill(user, items);
//
//        when(discountStrategyFactory.getDiscountStrategy(UserType.CUSTOMER)).thenReturn(discountStrategy);
//        when(discountStrategy.applyDiscount(bill)).thenReturn(10.0);
//
//        double netAmount = discountService.calculateNetPayableAmount(bill);
//
//        // Total amount: 400, percentage discount: 10 (mocked), flat discount: 20 (5*4)
//        assertEquals(370, netAmount, 0.01);
//    }
//
//    @Test
//    public void testFlatDiscountOnly() {
//        User user = new User("John",UserType.OTHER, LocalDate.now());
//        List<Item> items = Arrays.asList(
//                new Item("Item1", 150, true),
//                new Item("Item2", 200, true),
//                new Item("Item3", 150, true)
//        );
//        Bill bill = new Bill(user, items);
//
//        when(discountStrategyFactory.getDiscountStrategy(UserType.OTHER)).thenReturn(discountStrategy);
//        when(discountStrategy.applyDiscount(bill)).thenReturn(0.0);
//
//        double netAmount = discountService.calculateNetPayableAmount(bill);
//
//        // Total amount: 500, flat discount: 25 (5*5)
//        assertEquals(475, netAmount, 0.01);
//    }
//
//    @Test
//    public void testMixedItemsDiscount() {
//        User user = new User("John",UserType.EMPLOYEE, LocalDate.now());
//        List<Item> items = Arrays.asList(
//                new Item("Item1", 100, false),
//                new Item("Item2", 100, true),
//                new Item("Item3", 300, false)
//        );
//        Bill bill = new Bill(user, items);
//
//        when(discountStrategyFactory.getDiscountStrategy(UserType.EMPLOYEE)).thenReturn(discountStrategy);
//        when(discountStrategy.applyDiscount(bill)).thenReturn(120.0);
//
//        double netAmount = discountService.calculateNetPayableAmount(bill);
//
//        // Total amount: 500, percentage discount: 120 (mocked), flat discount: 25 (5*5)
//        assertEquals(355, netAmount, 0.01);
//    }
//
//    @Test
//    public void testNoItems() {
//        User user = new User("John",UserType.OTHER, LocalDate.now());
//        List<Item> items = Arrays.asList();
//        Bill bill = new Bill(user, items);
//
//        when(discountStrategyFactory.getDiscountStrategy(UserType.OTHER)).thenReturn(discountStrategy);
//        when(discountStrategy.applyDiscount(bill)).thenReturn(0.0);
//
//        double netAmount = discountService.calculateNetPayableAmount(bill);
//
//        // Total amount: 0, flat discount: 0
//        assertEquals(0, netAmount, 0.01);
//    }
//
//    @Test
//    public void testEmployeeWithNoDiscountableItems() {
//        User user = new User("John",UserType.EMPLOYEE, LocalDate.now());
//        List<Item> items = Arrays.asList(
//                new Item("Item1", 100, true),
//                new Item("Item2", 100, true),
//                new Item("Item3", 100, true)
//        );
//        Bill bill = new Bill(user, items);
//
//        when(discountStrategyFactory.getDiscountStrategy(UserType.EMPLOYEE)).thenReturn(discountStrategy);
//        when(discountStrategy.applyDiscount(bill)).thenReturn(0.0);
//
//        double netAmount = discountService.calculateNetPayableAmount(bill);
//
//        // Total amount: 300, percentage discount: 0 (mocked), flat discount: 15 (5*3)
//        assertEquals(285, netAmount, 0.01);
//    }
//
//    @Test
//    public void testBoundaryConditions() {
//        User user = new User("John",UserType.AFFILIATE, LocalDate.now());
//        List<Item> items = Arrays.asList(
//                new Item("Item1", 99, false),
//                new Item("Item2", 1, false)
//        );
//        Bill bill = new Bill(user, items);
//
//        when(discountStrategyFactory.getDiscountStrategy(UserType.AFFILIATE)).thenReturn(discountStrategy);
//        when(discountStrategy.applyDiscount(bill)).thenReturn(10.0);
//
//        double netAmount = discountService.calculateNetPayableAmount(bill);
//
//        // Total amount: 100, percentage discount: 10 (mocked), flat discount: 5 (5*1)
//        assertEquals(85, netAmount, 0.01);
//    }
//
//    @Test
//    public void testCustomerLessThanTwoYears() {
//        User user = new User("John",UserType.CUSTOMER, LocalDate.now().minusYears(1));
//        List<Item> items = Arrays.asList(
//                new Item("Item1", 100, false),
//                new Item("Item2", 100, true),
//                new Item("Item3", 100, false)
//        );
//        Bill bill = new Bill(user, items);
//
//        when(discountStrategyFactory.getDiscountStrategy(UserType.CUSTOMER)).thenReturn(discountStrategy);
//        when(discountStrategy.applyDiscount(bill)).thenReturn(0.0);
//
//        double netAmount = discountService.calculateNetPayableAmount(bill);
//
//        // Total amount: 300, percentage discount: 0 (mocked), flat discount: 15 (5*3)
//        assertEquals(285, netAmount, 0.01);
//    }
//}