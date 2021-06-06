package com.aranhid.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    ArrayList<Category> categories;
    ArrayList<Product> products;

    ListView categoryList, productsList;
    CategoryAdapter categoryAdapter;
    ProductsAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Database.create(this, false);

        categoryList = findViewById(R.id.categoryList);
        productsList = findViewById(R.id.productsList);

        categories = new ArrayList<>();
        products = new ArrayList<>();

        categoryAdapter = new CategoryAdapter(categories);
        categoryList.setAdapter(categoryAdapter);

        productsAdapter = new ProductsAdapter(products);
        productsList.setAdapter(productsAdapter);

        getCategories();
        getProducts();

    }

    public void updateCategoriesAdapter() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                categoryAdapter.notifyDataSetChanged();
            }
        });
    }

    public void updateProductsAdapter() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                productsAdapter.notifyDataSetChanged();
            }
        });
    }

    public void getCategories() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                CategoryInterface categoryInterface = database.categoryInterface();
                categories.clear();
                categories.addAll(categoryInterface.selectAll());
                updateCategoriesAdapter();
                if (categories.isEmpty()) {
                    insertCategories();
                }
                Log.d("TAG", categories.toString());
            }
        });
    }

    public void getProducts() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                ProductInterface productInterface = database.productInterface();
                products.clear();
                products.addAll(productInterface.selectAll());
                updateProductsAdapter();
                if (products.isEmpty()) {
                    insertProducts();
                }
                Log.d("TAG", products.toString());
            }
        });
    }

    public void getProductsByCategory(int category_id) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                ProductInterface productInterface = database.productInterface();
                products.clear();
                products.addAll(productInterface.findByCategory(category_id));
                updateProductsAdapter();
                Log.d("TAG", products.toString());
            }
        });
    }

    public  void  insertCategories() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                CategoryInterface categoryInterface = database.categoryInterface();
                categoryInterface.insert(new Category("Все"));
                categoryInterface.insert(new Category("Смартфоны"));
                categoryInterface.insert(new Category("Ноутбуки"));
                categoryInterface.insert(new Category("Видеокарты"));
                getCategories();
            }
        });
        Log.d("TAG", "category inserted");
    }

    public  void  insertProducts() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                ProductInterface productInterface = database.productInterface();
                productInterface.insert(new Product("Xiaomi Mi 9T Pro", 2));
                productInterface.insert(new Product("Samsung Galaxy S21", 2));
                productInterface.insert(new Product("Xiaomi Mi 11", 2));
                productInterface.insert(new Product("Lenovo IdeaPad 520", 3));
                productInterface.insert(new Product("Asus TUF", 3));
                productInterface.insert(new Product("HP Pavilion Gaming 15", 3));
                productInterface.insert(new Product("Nvidia GeForce GTX 1050 TI", 4));
                productInterface.insert(new Product("AMD Radeon RX580", 4));
                productInterface.insert(new Product("Intel HD Graphics 4000", 4));
                getProducts();
            }
        });
    }
}