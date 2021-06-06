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
                categoryInterface.insert(new Category("Все", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/Republic_Of_Korea_Broadcasting-TV_Rating_System%28ALL%29.svg/1200px-Republic_Of_Korea_Broadcasting-TV_Rating_System%28ALL%29.svg.png"));
                categoryInterface.insert(new Category("Смартфоны", "https://png.pngtree.com/png-clipart/20190920/original/pngtree-hand-drawn-smartphone-illustration-png-image_4649731.jpg"));
                categoryInterface.insert(new Category("Ноутбуки", "https://www.ixbt.com/img/n1/news/2019/0/2/Notebook-9-Pro_main_1_large.jpg"));
                categoryInterface.insert(new Category("Видеокарты", "https://3dnews.ru/assets/external/illustrations/2020/08/26/1019079/gpumainjpr.jpg"));
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
                productInterface.insert(new Product("Xiaomi Mi 9T Pro", 2, "https://i01.appmifile.com/webfile/globalimg/Echo/blue-Mi9T-Pro.png"));
                productInterface.insert(new Product("Samsung Galaxy S21", 2, "https://images.samsung.com/is/image/samsung/p6pim/ru/galaxy-s21/gallery/ru-galaxy-s21-5g-g991-sm-g991bzvdser-368806360?$720_576_PNG$"));
                productInterface.insert(new Product("Xiaomi Mi 11", 2, "https://avatars.mds.yandex.net/get-mpic/4055817/img_id3050302773406756013.jpeg/13hq"));
                productInterface.insert(new Product("Lenovo IdeaPad 520", 3, "https://www.notebookcheck-ru.com/uploads/tx_nbc2/2001535837.jpeg"));
                productInterface.insert(new Product("Asus TUF", 3, "https://www.notebookcheck-ru.com/uploads/tx_nbc2/asus-tuf-gaming-a15-fx506iu-bq225-amd-ryzen-7-4800h-16gb-512gb-ssd-gtx1660ti-156_03.jpg"));
                productInterface.insert(new Product("HP Pavilion Gaming 15", 3, "https://www.notebookcheck-ru.com/uploads/tx_nbc2/4zu3_HP_Pavilion_Gaming_15_ec.jpg"));
                productInterface.insert(new Product("Nvidia GeForce GTX 1050 TI", 4, "https://ae01.alicdn.com/kf/H26a47a69955c4ea4a204415e0b0d2f2bG/Gigabyte-GTX-1050-Ti-CN-4-NVIDIA-GeForce-GTX-1050Ti-GDDR5-128bit-PCI.jpg"));
                productInterface.insert(new Product("AMD Radeon RX580", 4, "https://www.ixbt.com/video4/images/polaris20/rx580-front.jpg"));
                productInterface.insert(new Product("Intel HD Graphics 4000", 4, "https://driverslab.ru/images/post/Intel_HD_4000_Graphics.jpg"));
                getProducts();
            }
        });
    }
}