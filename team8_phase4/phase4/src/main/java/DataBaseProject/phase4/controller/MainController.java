package DataBaseProject.phase4.controller;

import DataBaseProject.phase4.domain.LikeInfo;
import DataBaseProject.phase4.domain.NormalUser;
import DataBaseProject.phase4.domain.PaymentType;
import DataBaseProject.phase4.domain.Product;
import DataBaseProject.phase4.domain.PurchaseTransaction;
import DataBaseProject.phase4.domain.Review;
import DataBaseProject.phase4.domain.ShoppingCart;
import DataBaseProject.phase4.repository.Status;
import DataBaseProject.phase4.service.JoinService;
import DataBaseProject.phase4.service.ProductAndLikeInfoService;
import DataBaseProject.phase4.service.LoginService;
import DataBaseProject.phase4.service.ReviewService;
import DataBaseProject.phase4.service.ShoppingCartService;
import DataBaseProject.phase4.service.TransactionService;
import DataBaseProject.phase4.service.UpdateInfoService;
import DataBaseProject.phase4.service.UserService;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Controller
public class MainController {
    private final JoinService joinService;
    private final LoginService loginService;
    private final ProductAndLikeInfoService likeInfoService;
    private final UserService userService;
    private final UpdateInfoService updateInfoService;

    private final TransactionService transactionService;

    private final ShoppingCartService shoppingCartService;

    private final ReviewService reviewService;


    @Autowired
    public MainController(JoinService joinService, LoginService loginService,
            ProductAndLikeInfoService likeInfoService, UserService userService,
            UpdateInfoService updateInfoService, ShoppingCartService shoppingCartService,
            TransactionService transactionService, ReviewService reviewService) {
        this.joinService = joinService;
        this.loginService = loginService;
        this.likeInfoService = likeInfoService;
        this.userService = userService;
        this.updateInfoService = updateInfoService;
        this.transactionService = transactionService;
        this.shoppingCartService = shoppingCartService;
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public String loginMainPage(Model model){
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");
        return "loginPage";
    }
    @GetMapping("/signup")
    public String signupPage(Model model){
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");
        return "signup";
    }
    @PostMapping("/join")
    public String join(NormalUser normalUser, Model model){
        System.out.println(normalUser.getId());
        System.out.println(normalUser.getPassWord());
        System.out.println(normalUser.getEmailAddress());
        System.out.println(normalUser.getPhoneNumber());
        System.out.println(normalUser.getName());
        System.out.println(normalUser.getAge());
        System.out.println(normalUser.getAddress());

        Status status = joinService.join(normalUser);

        if(status == Status.SUCCESS) {
            model.addAttribute("error", "");
            return "loginPage";
        }
        else{
            model.addAttribute("error", "이미 존재하는 회원입니다. 다시 확인해주세요.");
            return "signup";
        }
    }

    @GetMapping("/mainPage")
    public String mainPage(Model model){
        System.out.println("hello");
        ArrayList<Product> temp = likeInfoService.getTop10Product();

        for (Product item : temp){
            String[] parts = item.getProductPhotoImage().split("/");
            item.setProductPhotoImage(parts[parts.length-1]);
            System.out.println(parts[parts.length-1]);
            System.out.println(item.getProductName());
        }
        model.addAttribute("items", temp);
        return "mainPage";
    }

    @GetMapping("/userInfo")
    public String userInfo(@CookieValue(name = "userId", required = false) String userId, Model model){
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");
        NormalUser normalUser = userService.getUserInfo(userId);
        model.addAttribute("normalUser", normalUser);

        return "userEditPage";
    }

    @GetMapping("/purchase")
    public String purchaseProduct(@CookieValue(name = "userId", required = false) String userId, Model model){
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");
        ArrayList<PurchaseTransaction> arr = transactionService.getTransactionInfos(userId);
        ArrayList<Product> items = new ArrayList<>();
        for (PurchaseTransaction purchaseTransaction : arr){
            Product item = likeInfoService.getProductInfo(purchaseTransaction.getProductId());
            String[] parts = item.getProductPhotoImage().split("/");
            item.setProductPhotoImage(parts[parts.length-1]);
            items.add(item);
        }
        model.addAttribute("items", items);
        return "purchasePage";
    }

    @GetMapping("/review")
    public String reviewProduct(@CookieValue(name = "userId", required = false) String userId, Model model){
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");
        ArrayList<Review> reviews = reviewService.getReviewInfos(userId);
        ArrayList<Product> products = new ArrayList<>();

        for (Review review : reviews){
            Product temp = likeInfoService.getProductInfo(review.getProductId());
            String[] parts = temp.getProductPhotoImage().split("/");
            temp.setProductPhotoImage(parts[parts.length-1]);
            products.add(temp);
        }

        model.addAttribute("items", products);
        model.addAttribute("reviews", reviews);
        return "review";
    }

    @GetMapping("/favorite")
    public String favoriteProduct(@CookieValue(name = "userId", required = false) String userId, Model model){
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");
        ArrayList<LikeInfo> likeInfos = likeInfoService.getLikeInfos(userId);
        ArrayList<Product> items = new ArrayList<>();

        for(LikeInfo likeInfo : likeInfos){
            Product item = likeInfoService.getProductInfo(likeInfo.getProductId());
            String[] parts = item.getProductPhotoImage().split("/");
            item.setProductPhotoImage(parts[parts.length-1]);
            items.add(item);
        }
        model.addAttribute("items", items);

        return "favoritePage";
    }

    @GetMapping("/shoppingCart")
    public String myShoppingCart(@CookieValue(name = "userId", required = false) String userId, Model model){
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");
        ArrayList<ShoppingCart> arr = shoppingCartService.checkProductsInShoppingCart(userId);
        ArrayList<Product> items = new ArrayList<>();
        for(ShoppingCart shoppingCart : arr){
            Product item = likeInfoService.getProductInfo(shoppingCart.getProductId());
            String[] parts = item.getProductPhotoImage().split("/");
            item.setProductPhotoImage(parts[parts.length-1]);
            items.add(item);
        }

        model.addAttribute("items", items);
        return "shoppingCart";
    }

    @GetMapping("/productDetail/{productId}")
    public String showProductPage(@PathVariable Long productId, Model model) {
        Product tProduct = likeInfoService.getProductInfo(productId);
        String[] parts = tProduct.getProductPhotoImage().split("/");
        tProduct.setProductPhotoImage(parts[parts.length-1]);
        model.addAttribute("tProduct", tProduct);
        return "productDetailPage";
    }

    @PostMapping("/login")
    public String login(String userId, String pw, Model model, RedirectAttributes redirectAttributes, HttpServletResponse response){
        model.addAttribute("error", ""); // 에러 속성 초기화
        Status status = loginService.normalLogin(userId, pw);
        System.out.println(userId);
        System.out.println(pw);
        if (status == Status.FAIL){
            model.addAttribute("error", "아이디 혹은 비밀번호가 맞지 않습니다. 다시 확인해주세요.");
            return "loginPage";
        }
        else{
            Cookie cookie = new Cookie("userId", userId);
            cookie.setMaxAge(60 * 60);  // 쿠키 유효 시간 : 1시간
            response.addCookie(cookie);
            System.out.println("Success");
            ArrayList<Product> temp = likeInfoService.getTop10Product();

            for (Product item : temp){
                String[] parts = item.getProductPhotoImage().split("/");
                item.setProductPhotoImage(parts[parts.length-1]);
                System.out.println(parts[parts.length-1]);
                System.out.println(item.getProductName());
            }
            model.addAttribute("items", temp);
            redirectAttributes.addFlashAttribute("items", temp);
            return "redirect:/mainPage";
        }
    }
    @GetMapping("/likeProduct/{productId}")
    public String likeProduct(@CookieValue(name = "userId", required = false) String userId, @PathVariable Long productId, Model model){
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");
        Status status = likeInfoService.doLike(userId, productId);
        if (status == Status.SUCCESS) {
            System.out.println("좋아요 완료");
            Product tProduct = likeInfoService.getProductInfo(productId);
            String[] parts = tProduct.getProductPhotoImage().split("/");
            tProduct.setProductPhotoImage(parts[parts.length - 1]);
            model.addAttribute("tProduct", tProduct);
            model.addAttribute("success", "좋아요가 등록되었습니다.");
            return "productDetailPage";
        }
        else {
            ArrayList<LikeInfo> arr = likeInfoService.getLikeInfos(userId);
            for(LikeInfo likeInfo : arr){
                if (likeInfo.getProductId().equals(productId)){
                    likeInfoService.unDoLike(likeInfo);
                    break;
                }
            }

            Product tProduct = likeInfoService.getProductInfo(productId);
            String[] parts = tProduct.getProductPhotoImage().split("/");
            tProduct.setProductPhotoImage(parts[parts.length - 1]);
            model.addAttribute("tProduct", tProduct);
            model.addAttribute("error", "좋아요를 삭제했습니다.");
            return "productDetailPage";
        }

    }
    @GetMapping("/customerReview/{productId}")
    public String customerReview(@PathVariable Long productId, Model model){
        Product item = likeInfoService.getProductInfo(productId);
        model.addAttribute("item", item);
        return "customerReview";
    }
    @GetMapping("/buyProduct/{productId}")
    public String buyProduct(@CookieValue(name = "userId", required = false) String userId, @PathVariable Long productId, Model model){
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");
        Product product = likeInfoService.getProductInfo(productId);
        LocalDateTime t = LocalDateTime.now();
        PurchaseTransaction purchaseTransaction = new PurchaseTransaction((long) (t.toString() + userId).hashCode(),
                userId, productId, product.getProductPrice(),
                PaymentType.CARD, Time.valueOf(t.toLocalTime()));
        Status status = transactionService.recordTransaction(purchaseTransaction);
        if (status == Status.SUCCESS) {
            System.out.println("구매 완료");
            Product tProduct = likeInfoService.getProductInfo(productId);
            String[] parts = tProduct.getProductPhotoImage().split("/");
            tProduct.setProductPhotoImage(parts[parts.length - 1]);
            model.addAttribute("tProduct", tProduct);
            model.addAttribute("success", "구매를 완료하였습니다.");
            return "productDetailPage";
        }
        else{
            Product tProduct = likeInfoService.getProductInfo(productId);
            String[] parts = tProduct.getProductPhotoImage().split("/");
            tProduct.setProductPhotoImage(parts[parts.length - 1]);
            model.addAttribute("tProduct", tProduct);
            model.addAttribute("error", "구매 오류가 발생했습니다.");
            return "productDetailPage";
        }
    }

    @GetMapping("addShoppingCart/{productId}")
    public String addShoppingCart(@CookieValue(name = "userId", required = false) String userId, @PathVariable Long productId, Model model){
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");
        Status status = shoppingCartService.addProduct(userId, productId, 1L);
        if (status == Status.SUCCESS) {
            System.out.println("장바구니 추가 완료");
            Product tProduct = likeInfoService.getProductInfo(productId);
            String[] parts = tProduct.getProductPhotoImage().split("/");
            tProduct.setProductPhotoImage(parts[parts.length - 1]);
            model.addAttribute("tProduct", tProduct);
            model.addAttribute("success", "장바구니에 저장이 완료되었습니다.");
            return "productDetailPage";
        }
        else{
            Product tProduct = likeInfoService.getProductInfo(productId);
            String[] parts = tProduct.getProductPhotoImage().split("/");
            tProduct.setProductPhotoImage(parts[parts.length - 1]);
            model.addAttribute("tProduct", tProduct);
            model.addAttribute("error", "장바구니에 저장을 실패했습니다.");
            return "productDetailPage";
        }
    }
    @GetMapping("/buyAllProduct")
    public String buyAllProduct(@CookieValue(name = "userId", required = false) String userId, Model model){
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");
        try {
            ArrayList<ShoppingCart> temp = shoppingCartService.buyProductsInShoppingCart(userId);
            for (ShoppingCart shoppingCart : temp){
                Product product = likeInfoService.getProductInfo(shoppingCart.getProductId());
                LocalDateTime t = LocalDateTime.now();
                PurchaseTransaction purchaseTransaction = new PurchaseTransaction((long) (t.toString() + userId).hashCode(),
                        userId, shoppingCart.getProductId(), product.getProductPrice(),
                        PaymentType.CARD, Time.valueOf(t.toLocalTime()));
                transactionService.recordTransaction(purchaseTransaction);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        ArrayList<ShoppingCart> arr = shoppingCartService.checkProductsInShoppingCart(userId);
        ArrayList<Product> items = new ArrayList<>();
        for(ShoppingCart shoppingCart : arr){
            Product item = likeInfoService.getProductInfo(shoppingCart.getProductId());
            String[] parts = item.getProductPhotoImage().split("/");
            item.setProductPhotoImage(parts[parts.length-1]);
            items.add(item);
        }

        model.addAttribute("items", items);
        model.addAttribute("success", "장바구니에 있는 모든 물건이 구매되었습니다.");
        return "shoppingCart";
    }
    @GetMapping("/deleteAllProduct")
    public String deleteAllProduct(@CookieValue(name = "userId", required = false) String userId, Model model){
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");
        shoppingCartService.deleteProduct(userId);
        ArrayList<ShoppingCart> arr = shoppingCartService.checkProductsInShoppingCart(userId);
        ArrayList<Product> items = new ArrayList<>();
        for(ShoppingCart shoppingCart : arr){
            Product item = likeInfoService.getProductInfo(shoppingCart.getProductId());
            String[] parts = item.getProductPhotoImage().split("/");
            item.setProductPhotoImage(parts[parts.length-1]);
            items.add(item);
        }

        model.addAttribute("items", items);
        model.addAttribute("success", "장바구니에 있는 모든 물건이 삭제되었습니다.");
        return "shoppingCart";
    }
    @PostMapping("/editUserInfo")
    public String login(@CookieValue(name = "userId", required = false) String userId, String pw, String name, String phoneNumber, String emailAddress, Long age, Model model){
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");
        System.out.println(pw);
        System.out.println(name);
        System.out.println(phoneNumber);
        System.out.println(emailAddress);
        if (pw != null)updateInfoService.updateInfo("password", pw, userId);
        if (name != null)updateInfoService.updateInfo("name", name, userId);
        if (phoneNumber != null)updateInfoService.updateInfo("phoneNumber", phoneNumber, userId);
        if (emailAddress != null)updateInfoService.updateInfo("emailAddress", emailAddress, userId);
        if (age != null)updateInfoService.updateInfo("age", age, userId);
        return "mainPage";
    }
    @PostMapping("/writeReview/{productId}")
    public String writeReview(@CookieValue(name = "userId", required = false) String userId, @PathVariable Long productId, String title, String body, RedirectAttributes redirectAttributes) {
        LocalDateTime t = LocalDateTime.now();
        reviewService.addReview(new Review((long)(userId + productId.toString() + t.toString()).hashCode(), userId, productId, body, Time.valueOf(t.toLocalTime())));

        System.out.println("리뷰 완료");

        ArrayList<PurchaseTransaction> arr = transactionService.getTransactionInfos(userId);
        ArrayList<Product> items = new ArrayList<>();
        for (PurchaseTransaction purchaseTransaction : arr){
            Product item = likeInfoService.getProductInfo(purchaseTransaction.getProductId());
            String[] parts = item.getProductPhotoImage().split("/");
            item.setProductPhotoImage(parts[parts.length-1]);
            items.add(item);
        }

        // Flash Attributes를 사용하여 데이터 전달
        redirectAttributes.addFlashAttribute("items", items);

        return "redirect:/purchase";
    }

}
