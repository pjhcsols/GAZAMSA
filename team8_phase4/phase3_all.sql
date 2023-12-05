GRANT ALL PRIVILEGES TO university;

conn university/comp322


drop table NormalUser cascade constraints;
drop table BrandUser cascade constraints;
drop table Admin cascade constraints;
drop table Category cascade constraints;
drop table Product cascade constraints;
drop table ShoppingCart cascade constraints;
drop table PurchaseTransaction cascade constraints;
drop table Like_Info cascade constraints;
drop table Image cascade constraints;
drop table Question cascade constraints;
drop table Answer cascade constraints;
drop table Review cascade constraints;
drop table ReviewComment cascade constraints;

commit;
-- CREATE TABLE statements
CREATE TABLE NormalUser (
    id VARCHAR(255) PRIMARY KEY,
    passWord VARCHAR(255) NOT NULL,
    emailAddress VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    rank INT DEFAULT 1,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    address VARCHAR(255)
);
commit;

CREATE TABLE BrandUser (
    id VARCHAR(255) PRIMARY KEY,
    passWord VARCHAR(255) NOT NULL,
    emailAddress VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(255) NOT NULL,
    rank INT DEFAULT 4,
    firmName VARCHAR(255) NOT NULL,
    firmPhoneNumber VARCHAR(255) NOT NULL,
    firmAddress VARCHAR(255) NOT NULL,
    businessRegistration VARCHAR(255) NOT NULL,
    firmWebUrl VARCHAR(255)
);
commit;
CREATE TABLE Admin (
    id VARCHAR(255) PRIMARY KEY,
    passWord VARCHAR(255) NOT NULL,
    emailAddress VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    rank INT DEFAULT 5
);
commit;
CREATE TABLE Category (
    categoryId INT PRIMARY KEY,
    category VARCHAR(255)
);
commit;
CREATE TABLE Product (
    productId INT PRIMARY KEY,
    productCategoryId INT,
    brandUserId VARCHAR(255),
    productName VARCHAR(255) NOT NULL,
    productPrice NUMBER(10, 2) NOT NULL,
    productDesc CLOB NOT NULL,
    productPhotoImage VARCHAR(255) NOT NULL,
    FOREIGN KEY(brandUserId) REFERENCES BrandUser(id) ON DELETE CASCADE,
    FOREIGN KEY(productCategoryId) REFERENCES Category(categoryId) ON DELETE SET NULL
);
commit;
CREATE TABLE ShoppingCart (
    orderId VARCHAR(255) PRIMARY KEY,
    userId VARCHAR(255),
    productId INT NOT NULL,
    amount INT NOT NULL,
    FOREIGN KEY(userId) REFERENCES NormalUser(id) ON DELETE CASCADE,
    FOREIGN KEY(productId) REFERENCES Product(productId) ON DELETE CASCADE
);
commit;
CREATE TABLE PurchaseTransaction (
    transactionId INT PRIMARY KEY,
    userId VARCHAR(255),
    productId INT NOT NULL,
    totalPrice NUMBER(10, 2) NOT NULL,
    paymentType VARCHAR(20) NOT NULL,
    purchaseTime TIMESTAMP NOT NULL,
    FOREIGN KEY(userId) REFERENCES NormalUser(id) ON DELETE CASCADE,
    FOREIGN KEY(productId) REFERENCES Product(productId) ON DELETE CASCADE
);
commit;
CREATE TABLE Like_Info (
    id INT PRIMARY KEY,
    userId VARCHAR(255),
    productId INT,
    FOREIGN KEY(userId) REFERENCES NormalUser(id) ON DELETE CASCADE,
    FOREIGN KEY(productId) REFERENCES Product(productId) ON DELETE CASCADE
);
commit;

CREATE TABLE Image (
    imageId INT PRIMARY KEY,
    productId INT,
    imagePath VARCHAR(255) NOT NULL,
    FOREIGN KEY(productId) REFERENCES Product(productId) ON DELETE CASCADE
);
commit;
CREATE TABLE Question (
    questionId INT PRIMARY KEY,
    userId VARCHAR(255),
    productId INT,
    question CLOB NOT NULL,
    uploadDate TIMESTAMP NOT NULL,
    FOREIGN KEY(userId) REFERENCES NormalUser(id) ON DELETE CASCADE,
    FOREIGN KEY(productId) REFERENCES Product(productId) ON DELETE CASCADE
);
commit;
CREATE TABLE Answer (
    answerId INT PRIMARY KEY,
    questionId INT,
    answer CLOB NOT NULL,
    uploadDate TIMESTAMP NOT NULL,
    FOREIGN KEY(questionId) REFERENCES Question(questionId) ON DELETE CASCADE
);
commit;
CREATE TABLE Review (
    reviewId INT PRIMARY KEY,
    userId VARCHAR(255),
    productId INT,
    review CLOB NOT NULL,
    reviewDate TIMESTAMP NOT NULL,
    FOREIGN KEY(userId) REFERENCES NormalUser(id) ON DELETE CASCADE,
    FOREIGN KEY(productId) REFERENCES Product(productId) ON DELETE CASCADE
);
commit;
CREATE TABLE ReviewComment (
    reviewCommentId INT PRIMARY KEY,
    reviewId INT,
    reviewComment CLOB NOT NULL,
    commentDate TIMESTAMP NOT NULL,
    FOREIGN KEY(reviewId) REFERENCES Review(reviewId) ON DELETE CASCADE
);
commit;


-- INSERT INTO statements
-- NormalUser table
BEGIN
    FOR i IN 1..500 LOOP
        INSERT INTO NormalUser (id, passWord, emailAddress, phoneNumber, rank, name, age, address)
        VALUES (
            'User' || LPAD(i, 3, '0'),
            'password' || LPAD(i, 3, '0'),
            'user' || LPAD(i, 3, '0') || '@example.com',
            '123-456-' || LPAD(i, 3, '0'),
            1,
            'User Name' || i,
            25 + MOD(i, 10),  -- Age
            'Address ' || i
        );
    END LOOP;
    COMMIT;
END;
/



-- BrandUser table
BEGIN
    FOR i IN 1..500 LOOP
        INSERT INTO BrandUser (id, passWord, emailAddress, phoneNumber, rank, firmName, firmPhoneNumber, firmAddress, businessRegistration, firmWebUrl)
        VALUES (
            'Brand' || LPAD(i, 3, '0'),
            'brandpass' || LPAD(i, 3, '0'),
            'brand' || LPAD(i, 3, '0') || '@brand.com',
            '555-555-' || LPAD(i, 3, '0'),
            4,
            'Brand Name ' || i,
            '555-555-' || LPAD(i, 3, '0'),  -- Firm phone number
            'Firm Address ' || i,
            'BR' || LPAD(i, 8, '0'),  -- Business registration
            'http://www.brand' || LPAD(i, 3, '0') || ' .com'
        );
    END LOOP;
    COMMIT;
END;
/



-- Admin table
BEGIN
    FOR i IN 1..500 LOOP
        INSERT INTO Admin (id, passWord, emailAddress, phoneNumber, rank)
        VALUES (
            'Admin' || LPAD(i, 3, '0'),
            'adminpass' || LPAD(i, 3, '0'),
            'admin' || LPAD(i, 3, '0') || '@admin.com',
            '777-777-' || LPAD(i, 3, '0'),
            5
        );
    END LOOP;
    COMMIT;
END;
/



-- Category table
BEGIN
    FOR i IN 1..500 LOOP
        INSERT INTO Category (categoryId, category)
        VALUES (i, 'Category ' || i);
    END LOOP;
    COMMIT;
END;
/


-- Product table
BEGIN
    FOR i IN 1..500 LOOP
        INSERT INTO Product (productId, productCategoryId, brandUserId, productName, productPrice, productDesc, productPhotoImage)
        VALUES (
            i,
            MOD(i, 10) + 1,  -- Product category
            'Brand' || LPAD(MOD(i, 10) + 1, 3, '0'),
            'Product Name ' || i,
            10.0 + (i * 2),
            'Product Description ' || i,
            'product' || LPAD(i, 3, '0') || '.jpg'  -- Product photo image
        );
    END LOOP;
    COMMIT;
END;
/



-- ShoppingCart table
BEGIN
    FOR i IN 1..100 LOOP
        INSERT INTO ShoppingCart (orderId, userId, productId, amount)
        VALUES (
            'Order' || LPAD(i, 3, '0'),
            'User' || LPAD(i, 3, '0'),
            MOD(i, 100) + 1,  -- Product ID
            MOD(i, 5) + 1  -- Amount
        );
    END LOOP;
    COMMIT;
END;
/


-- PurchaseTransaction table
BEGIN
    FOR i IN 1..500 LOOP
        INSERT INTO PurchaseTransaction (transactionId, userId, productId, totalPrice, paymentType, purchaseTime)
        VALUES (
            i,
            'User' || LPAD(MOD(i, 100) + 1, 3, '0'),
            MOD(i, 100) + 1,  -- Product ID
            20.0 + (i * 2),
            CASE WHEN MOD(i, 2) = 0 THEN 'Credit Card' ELSE 'PayPal' END,  -- Payment type
            TO_TIMESTAMP('2023-10-' || LPAD(MOD(i, 30) + 1, 2, '0') || ' 12:34:56', 'YYYY-MM-DD HH24:MI:SS')  -- Purchase time
        );
    END LOOP;
    COMMIT;
END;
/



-- Like table
BEGIN
    FOR i IN 1..500 LOOP
        INSERT INTO Like_Info (id, userId, productId)
        VALUES (i, 'User' || LPAD(i, 3, '0'), MOD(i, 100) + 1);  -- Product ID
    END LOOP;
    COMMIT;
END;
/



-- Image table
BEGIN
    FOR i IN 1..500 LOOP
        INSERT INTO Image (imageId, productId, imagePath)
        VALUES (
            i,
            MOD(i, 100) + 1,  -- Product ID
            'images/product' || LPAD(i, 3, '0') || '.jpg'
        );
    END LOOP;
    COMMIT;
END;
/


-- Question table
BEGIN
    FOR i IN 1..500 LOOP
        INSERT INTO Question (questionId, userId, productId, question, uploadDate)
        VALUES (
            i,
            'User' || LPAD(MOD(i, 100) + 1, 3, '0'),
            MOD(i, 100) + 1,  -- Product ID
            'Question ' || i,
            TO_TIMESTAMP('2023-10-' || LPAD(MOD(i, 30) + 1, 2, '0') || ' 14:45:00', 'YYYY-MM-DD HH24:MI:SS')
        );
    END LOOP;
    COMMIT;
END;
/



-- Answer table
BEGIN
    FOR i IN 1..500 LOOP
        INSERT INTO Answer (answerId, questionId, answer, uploadDate)
        VALUES (
            i,
            i,  -- Question ID
            'Answer to Question ' || i,
            TO_TIMESTAMP('2023-10-' || LPAD(MOD(i, 30) + 1, 2, '0') || ' 15:30:00', 'YYYY-MM-DD HH24:MI:SS')
        );
    END LOOP;
    COMMIT;
END;
/


-- Review table
BEGIN
    FOR i IN 1..500 LOOP
        INSERT INTO Review (reviewId, userId, productId, review, reviewDate)
        VALUES (
            i,
            'User' || LPAD(MOD(i, 100) + 1, 3, '0'),
            MOD(i, 100) + 1,  -- Product ID
            'Review ' || i,
            TO_TIMESTAMP('2023-10-' || LPAD(MOD(i, 30) + 1, 2, '0') || ' 16:15:00', 'YYYY-MM-DD HH24:MI:SS')
        );
    END LOOP;
    COMMIT;
END;
/


-- ReviewComment table
BEGIN
    FOR i IN 1..500 LOOP
        INSERT INTO ReviewComment (reviewCommentId, reviewId, reviewComment, commentDate)
        VALUES (i, i, 'Comment on Review ' || i, TO_TIMESTAMP('2023-10-' || LPAD(MOD(i, 30) + 1, 2, '0') || ' 17:00:00', 'YYYY-MM-DD HH24:MI:SS'));
    END LOOP;
    COMMIT;
END;
/



/*[일반, 브랜드] 유저 기본 기능
회원가입 - 추가
로그인 - 추가
회원 수정 - 추가

[일반] 내 정보 보기 
장바구니 확인 - 3번 쿼리 실행 후 로그인된 유저의 아이디와 비교하여 반환
구매목록 확인 - 4번 쿼리 실행 후 로그인된 유저의 아이디와 비교하여 반환
좋아요가 있는 상품 정보 확인 - 7번 쿼리 그대로 실행
내가 작성한 리뷰 확인하기 - 14번 쿼리 실행 후 아이디 비교(수정 필요)


옷 구입 - 추가
————————
상품 등록 - 추가
상품 수정 - 추가
상품 삭제 - 추가

———————-
일반유저랑 브랜드 관리 - 1번, 2번 쿼리 사용
*/

-- 회원가입
-- 아이디와 유저 타입을 입력받았다고 가정
select *
from normaluser u -- 일반 유저인지, 브랜드 유저인지 입력 받음
where u.id = 'User001'; -- 유저의 아이디를 입력 받음

-- 만약 중복이 없다면
insert into normaluser values('Woo', 'woo', ' ', ' ', 1, ' ', 0, ' ');


-- 로그인
-- 아이디와 패스워드와 유저 타입을 입력받았다고 가정
select *
from normaluser u -- 일반 유저인지, 브랜드 유저, 어드민인지 입력 받음
where u.id = 'User001' -- 유저의 아이디를 입력받음 
and u.passWord = 'password001'; -- 유저의 비밀번호를 입력받음


-- 회원 수정
-- 유저의 정보를 수정.
update NormalUser u -- 유저 타입을 입력 받음
set -- 변경하고자 하는 유저의 정
u.name = 'Woo'
where u.id = 'User001'; -- 유저의 아이


-- 내 개인 정보 확인
select *
from normaluser u
where u.id = 'User001'; -- 로그인한 유저의 아이디

-- 장바구니 확인, 아래 쿼리 이후 ID 비교 
select s.userid, s.orderid, s.productid, s.amount
from shoppingcart s, normaluser n
where n.id = s.userid;

-- 구매목록 확인, 아래 쿼리 이후 ID 비교
select n.id, pt.purchasetime, p.productid
from normaluser n, purchasetransaction pt, product p
where n.id = pt.userid
and pt.productid = p.productid;

-- 좋아요가 있는 상품 정보 확인 - 7번 쿼리 그대로 실행
select p.productname, p.productprice
from product p
where p.productid in (
    select l.productid
    from normaluser n, like_info l
    where n.id = l.userid
    and p.productid = l.productid
);

-- 내가 작성한 리뷰 확인하기 - 14번 쿼리 실행 후 아이디 비교(수정했음)
select n.id, t1.review
from (
    select r.userid, r.review
    from purchasetransaction pt, review r
    where pt.productid = r.productid
    and pt.userid = r.userid
) t1 join normaluser n on t1.userid = n.id;


-- 옷 구입 
insert into PurchaseTransaction values (12345, 'User001', 10, 0, 'creadit card', TO_DATE('2023-11-19', 'yyyy-mm-dd'));
update PurchaseTransaction pt
set totalPrice = (
    select p.productPrice
    from product p join PurchaseTransaction pt1 on p.productid = pt1.productid
    where pt1.transactionid = 12345
)
where pt.transactionid = 12345;


-- 상품 등록
insert into product values (12345, 1, 'Brand001', '김치맛치즈', 10000, '장난감입니다.', ' ');


-- 상품 수정, 우선 해당 브랜드 유저가 가진 제품 아이디와 제품 이름을 반환
select p.productId, p.productName
from product p join brandUser b on p.branduserid = b.id
where b.id = 'Brand001';

-- 해당 제품 아이디를 바탕으로 원하는 부분을 수정 
update product
set productDesc = '먹을 것이 아닙니다.'
where productId = 12345;


-- 상품 삭제 
delete from product
where productId = 12345;


-- 일반유저랑 브랜드 관리
select *
from branduser b;

delete from branduser
where id = 'Brand001';