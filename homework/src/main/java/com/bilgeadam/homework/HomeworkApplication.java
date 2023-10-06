package com.bilgeadam.homework;

import com.bilgeadam.homework.model.*;
import com.bilgeadam.homework.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
@RequiredArgsConstructor
public class HomeworkApplication implements CommandLineRunner {


    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ShipperRepository shipperRepository;


    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Sisteme hoş geldiniz....");
            System.out.println("Lütfen işlem seçiniz....");
            System.out.println("1-Çalışan Listele \n2-Ürün Listele \n3-Kargocu Listele \n4-Satış Yap \n5-Satışları Göster \nÇıkış için 0'ı tuşlayın");
            System.out.println("Seçim: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                getEmployeeList();
            } else if (choice == 2) {
                getProductList();
            } else if (choice == 3) {
                getShipperList();
            } else if (choice == 4) {
                getSales();
            } else if (choice==5) {
                getOrder();
            } else if (choice == 0) {
                System.out.println("Çıkış yapılıyor...");
                break;
            } else {
                System.out.println("Hatalı giriş");
            }

        }
    }

    public void getProductList() {
        System.out.println("Ürünler");
        List<Product> productList = productRepository.findAll();
        System.out.println("--Ürün ID--             --Ürün Adı--            --Ürün Fiyatı--");

        for (Product product : productList) {
            System.out.println(product.getProductId() + "             " + product.getProductName() + "            " + product.getUnitPrice());
        }
    }

    public void getEmployeeList() {
        System.out.println("Çalışanlar");
        List<Employee> employeeList = employeeRepository.findAll();

        for (Employee employee : employeeList) {
            System.out.println(employee.getEmployeeId() + " " + employee.getFirstName() + " " + employee.getLastName());
        }
    }

    public void getShipperList() {
        System.out.println("Kargo Firması");
        List<Shipper> shipperList = shipperRepository.findAll();
        for (Shipper shipper : shipperList) {
            System.out.println(shipper.getShipperId() + " " + shipper.getShipperName() + " " + shipper.getPhone());
        }
    }

    public void getOrder (){
        System.out.println("Satışlar");
        List<Order> orderList = orderRepository.findAll();

        System.out.println("--Sipariş No--     --Çalışan ID--       --Müşteri ID--      --Ürün ID--     --Fiyat--       --Miktar--      --Kargo ID--");

        for (Order order : orderList) {
            System.out.println(order.getOrderId()+"                         "+order.getEmployeeId()+"            "+order.getCustomerId()+"                "+order.getProductId()+"              "+order.getPrice()+"                    "+order.getQuantity()+"                 "+order.getShipId());
        }
    }

    public void getSales() {
        Order order = new Order();
        LocalDateTime now = LocalDateTime.now();
        Scanner scanner = new Scanner(System.in);
        //employeeid
        System.out.println("Satışı yapan çalışan ID'sini giriniz....");
        int selectedEmployee;
        while (true) {
            selectedEmployee = scanner.nextInt();
            if (!controlEmployeeId(selectedEmployee)) {
                System.out.println("Girilen ID'ye ait çalışan bilgisi bulunamadı. Tekrar deneyiniz...");
            } else {
                break;
            }
        }

        System.out.println("Satış yapılacak müşteri ID'sini giriniz....");
        String selectedCustomer;
        while (true) {
            selectedCustomer = scanner.next();
            if (!controlCustomerId(selectedCustomer)) {
                System.out.println("Girilen ID'ye ait müşteri bilgisi bulunamadı. Tekrar deneyiniz...");
            } else {
                break;
            }
        }

        System.out.println("Satış yapılacak ürün ID'sini giriniz....");
        int selectedProduct;
        while (true) {
            selectedProduct = scanner.nextInt();
            if (!controlProductId(selectedProduct)) {
                System.out.println("Girilen ID'ye ait ürün bilgisi bulunamadı. Tekrar deneyiniz...");
            } else {
                break;
            }
        }

        System.out.println("Satılacak ürün adedi giriniz...");
        int quantity = scanner.nextInt();

        System.out.println("Uygulanacak indirim oranını giriniz...");
        double discount = scanner.nextDouble();

        double price = productRepository.findByProductId(selectedProduct).orElseThrow().getUnitPrice();

        System.out.println("Gönderim için kargo şirketi seçiniz....");
        int selectedShipper;
        while (true) {
            selectedShipper = scanner.nextInt();
            if (!controlShipperId(selectedShipper)) {
                System.out.println("Girilen ID'ye ait kargo firması bulunamadı. Tekrar deneyiniz...");
            } else {
                break;
            }
        }

        LocalDateTime orderDate = now;

        order.setEmployeeId(selectedEmployee);
        order.setCustomerId(selectedCustomer);
        order.setProductId(selectedProduct);
        order.setQuantity(quantity);
        order.setDiscount(discount);
        order.setShipId(selectedShipper);
        order.setOrderDate(orderDate);
        order.setPrice(price);

        orderRepository.save(order);
        System.out.println("Satış başarılı... Satış numarası: " + order.getOrderId());
    }

    public boolean controlEmployeeId(int employeeId) {
        boolean result;
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        result = employee.isPresent();

        return result;
    }

    public boolean controlProductId(int productId) {
        boolean result;
        Optional<Product> product = productRepository.findById(productId);
        result = product.isPresent();

        return result;
    }

    public boolean controlCustomerId(String customerId) {
        boolean result;
        Optional<Customer> customer = customerRepository.findById(customerId);
        result = customer.isPresent();

        return result;
    }

    public boolean controlShipperId(int shipperId) {
        boolean result;
        Optional<Shipper> shipper = shipperRepository.findById(shipperId);
        result = shipper.isPresent();

        return result;
    }

}
