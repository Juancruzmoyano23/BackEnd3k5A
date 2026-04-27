CREATE TABLE IF NOT EXISTS Categories (
                                          categoryid INTEGER PRIMARY KEY,
                                          categoryname TEXT NOT NULL,
                                          description TEXT
);

CREATE TABLE IF NOT EXISTS Products (
                                        productid INTEGER PRIMARY KEY,
                                        productname TEXT NOT NULL,
                                        categoryid INTEGER,
                                        quantityperunit TEXT,
                                        unitprice REAL,
                                        unitsinstock INTEGER,
                                        unitsonorder INTEGER,
                                        reorderlevel INTEGER,
                                        discontinued TEXT,
                                        FOREIGN KEY (categoryid) REFERENCES Categories(categoryid)
    );

CREATE TABLE IF NOT EXISTS sqlite_sequence (
                                               name TEXT PRIMARY KEY,
                                               seq INTEGER
);
