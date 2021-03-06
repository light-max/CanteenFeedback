package com.pky.canteen.api;

import com.pky.canteen.async.Async;
import com.pky.canteen.data.result.CollectorPager;
import com.pky.canteen.data.result.ComplaintDetails;
import com.pky.canteen.data.result.Cuisine;
import com.pky.canteen.data.result.DishDetails;
import com.pky.canteen.data.result.DishDetailsPager;
import com.pky.canteen.data.result.Feedback;
import com.pky.canteen.data.result.FeedbackMsg;
import com.pky.canteen.data.result.MenuItemPager;
import com.pky.canteen.data.result.Opinion;
import com.pky.canteen.data.result.RemarkPager;
import com.pky.canteen.data.result.StallDetails;
import com.pky.canteen.data.result.Student;
import com.pky.canteen.data.result.Teacher;
import com.pky.canteen.net.request.RequestBuilder;

import java.io.File;
import java.util.List;

public class Api {
    public static Async.Builder<Student> sLogin(String uid, String password) {
        return ExRequestBuilder.post("/s/login")
                .form()
                .field("uid", uid)
                .field("password", password)
                .<ExRequestBuilder>as()
                .async(Student.class);
    }

    public static Async.Builder<Teacher> tLogin(String uid, String password) {
        return ExRequestBuilder.post("/t/login")
                .form()
                .field("uid", uid)
                .field("password", password)
                .<ExRequestBuilder>as()
                .async(Teacher.class);
    }

    public static Async.Builder<?> sRegister(String uid, String name, String password) {
        return ExRequestBuilder.post("/s/register")
                .form()
                .field("uid", uid)
                .field("name", name)
                .field("password", password)
                .async();
    }

    public static Async.Builder<?> tRegister(String uid, String name, String password) {
        return ExRequestBuilder.post("/t/register")
                .form()
                .field("uid", uid)
                .field("name", name)
                .field("password", password)
                .async();
    }

    public static Async.Builder<MenuItemPager> getTodayMenu(int page) {
        return ExRequestBuilder.get("/menu/today/list/{n}")
                .path("n", page)
                .<ExRequestBuilder>as()
                .async(MenuItemPager.class);
    }

    public static Async.Builder<DishDetails> getDishDetails(int id) {
        return ExRequestBuilder.get("/dish/details/{id}")
                .path("id", id)
                .<ExRequestBuilder>as()
                .async(DishDetails.class);
    }

    public static Async.Builder<DishDetailsPager> getDishList(int page) {
        return ExRequestBuilder.get("/dish/list/{n}")
                .path("n", page)
                .<ExRequestBuilder>as()
                .async(DishDetailsPager.class);
    }

    public static Async.Builder<DishDetailsPager> getDishListByStallId(int page, int stallId) {
        return ExRequestBuilder.get("/dish/list/{n}")
                .path("n", page)
                .param("stallId", stallId)
                .<ExRequestBuilder>as()
                .async(DishDetailsPager.class);
    }

    public static Async.Builder<RemarkPager> getRemarkList(int page, int dishId) {
        return ExRequestBuilder.get("/remark/list/{n}")
                .path("n", page)
                .param("dishId", dishId)
                .<ExRequestBuilder>as()
                .async(RemarkPager.class);
    }

    public static Async.Builder<RemarkPager> getReplyList(int page, int parentId) {
        return ExRequestBuilder.get("/remark/list/{n}")
                .path("n", page)
                .param("parentId", parentId)
                .<ExRequestBuilder>as()
                .async(RemarkPager.class);
    }

    public static Async.Builder<Integer> getRemarkCount(int dishId) {
        return ExRequestBuilder.get("/remark/count")
                .param("dishId", dishId)
                .<ExRequestBuilder>as()
                .async(Integer.class);
    }

    public static Async.Builder<?> sendRemark(int dishId, String content) {
        return ExRequestBuilder.post("/api/remark")
                .form()
                .field("dishId", dishId)
                .field("content", content)
                .<ExRequestBuilder>as()
                .async();
    }

    public static Async.Builder<?> sendReply(int dishId, int parentId, String content) {
        return ExRequestBuilder.post("/api/remark")
                .form()
                .field("dishId", dishId)
                .field("parentId", parentId)
                .field("content", content)
                .<ExRequestBuilder>as()
                .async();
    }

    public static Async.Builder<CollectorPager> getCollector(int page, int dishId) {
        return ExRequestBuilder.get("/collect/list/{n}")
                .path("n", page)
                .param("dishId", dishId)
                .<ExRequestBuilder>as()
                .async(CollectorPager.class);
    }

    public static Async.Builder<Integer> getCollectorCount(int dishId) {
        return ExRequestBuilder.get("/collect/count")
                .param("dishId", dishId)
                .<ExRequestBuilder>as()
                .async(Integer.class);
    }

    public static Async.Builder<Boolean> checkCollect(int dishId) {
        return ExRequestBuilder.get("/api/collect/check")
                .param("dishId", dishId)
                .<ExRequestBuilder>as()
                .async(Boolean.class);
    }

    public static Async.Builder<Boolean> postCollect(int dishId) {
        return ExRequestBuilder.post("/api/collect")
                .form()
                .field("dishId", dishId)
                .<ExRequestBuilder>as()
                .async(Boolean.class);
    }

    public static Async.Builder<?> postOpinion(
            int dishId,
            String content,
            List<File> images,
            File video
    ) {
        RequestBuilder request = ExRequestBuilder.post("/api/opinion")
                .form()
                .field("dishId", dishId)
                .field("content", content);
        if (!images.isEmpty()) {
            request.field("images", images);
        }
        if (video != null) {
            request.field("video", video);
        }
        return request.async();
    }

    public static Async.Builder<List<Cuisine>> getCuisineList() {
        return ExRequestBuilder.get("/cuisine/list")
                .<ExRequestBuilder>as()
                .asyncList(Cuisine.class);
    }

    public static Async.Builder<DishDetailsPager> getDishListByCuisine(int page, int cuisineId) {
        return ExRequestBuilder.get("/cuisine/dish/list/{n}")
                .path("n", page)
                .param("cuisineId", cuisineId)
                .<ExRequestBuilder>as()
                .async(DishDetailsPager.class);
    }

    public static Async.Builder<StallDetails> getStallDetails(int stallId) {
        return ExRequestBuilder.get("/stall/details/{id}")
                .path("id", stallId)
                .<ExRequestBuilder>as()
                .async(StallDetails.class);
    }

    public static Async.Builder<List<StallDetails>> getStallAll() {
        return ExRequestBuilder.get("/stall/list/all")
                .<ExRequestBuilder>as()
                .asyncList(StallDetails.class);
    }

    public static Async.Builder<List<FeedbackMsg>> getFeedbackMsgAll() {
        return ExRequestBuilder.get("/api/feedback/msg/all")
                .<ExRequestBuilder>as()
                .asyncList(FeedbackMsg.class);
    }

    public static Async.Builder<?> readFeedbackMsg(int id) {
        return ExRequestBuilder.post("/api/feedback/msg/read/{id}")
                .path("id", id)
                .async();
    }

    public static Async.Builder<?> unreadFeedbackMsg(int id) {
        return ExRequestBuilder.post("/api/feedback/msg/unread/{id}")
                .path("id", id)
                .async();
    }

    public static String getHeadUrl(String uid) {
        return ExRequestBuilder.getUrl("/head/" + uid);
    }

    public static void logout() {
        ExRequestBuilder.post("/api/logout")
                .async()
                .run();
    }

    public static Async.Builder<?> setHeadImage(File file) {
        return ExRequestBuilder.post("/api/head")
                .form()
                .field("file", file)
                .<ExRequestBuilder>as()
                .async();
    }

    public static Async.Builder<?> setUserInfo(String type, Object value) {
        return ExRequestBuilder.put("/api/info/{type}")
                .path("type", type)
                .form()
                .field("value", value)
                .async();
    }

    public static Async.Builder<Teacher> getUserTeacher() {
        return ExRequestBuilder.get("/api/info/teacher")
                .<ExRequestBuilder>as()
                .async(Teacher.class);
    }

    public static Async.Builder<Student> getUserStudent() {
        return ExRequestBuilder.get("/api/info/student")
                .<ExRequestBuilder>as()
                .async(Student.class);
    }

    public static Async.Builder<List<DishDetails>> getCollectAll() {
        return ExRequestBuilder.get("/api/collect/list/all")
                .<ExRequestBuilder>as()
                .asyncList(DishDetails.class);
    }

    public static Async.Builder<List<Opinion>> getOpinionAll() {
        return ExRequestBuilder.get("/api/opinion/list/all")
                .<ExRequestBuilder>as()
                .asyncList(Opinion.class);
    }

    public static Async.Builder<Opinion> getOpinion(int id) {
        return ExRequestBuilder.get("/api/opinion/details/{id}")
                .path("id", id)
                .<ExRequestBuilder>as()
                .async(Opinion.class);
    }

    public static Async.Builder<Feedback> getFeedbackById(int id) {
        return ExRequestBuilder.get("/feedback/{id}")
                .path("id", id)
                .<ExRequestBuilder>as()
                .async(Feedback.class);
    }

    public static Async.Builder<ComplaintDetails> getComplaintDetails(int feedbackId) {
        return ExRequestBuilder.get("/api/complaint")
                .param("feedbackId", feedbackId)
                .<ExRequestBuilder>as()
                .async(ComplaintDetails.class);
    }

    public static Async.Builder<?> sendComplaint(int feedbackId, String des) {
        return ExRequestBuilder.post("/api/complaint")
                .form()
                .field("feedbackId", feedbackId)
                .field("des", des)
                .async();
    }

    public static Async.Builder<List<ComplaintDetails>> getComplaintListAll() {
        return ExRequestBuilder.get("/api/complaint/list/all")
                .<ExRequestBuilder>as()
                .asyncList(ComplaintDetails.class);
    }

    public static Async.Builder<List<StallDetails>> searchStall(String value) {
        return ExRequestBuilder.get("/search/stall")
                .param("value", value)
                .<ExRequestBuilder>as()
                .asyncList(StallDetails.class);
    }

    public static Async.Builder<DishDetailsPager> searchDish(int page, String value) {
        return ExRequestBuilder.get("/search/dish/{page}")
                .path("page", page)
                .param("value", value)
                .<ExRequestBuilder>as()
                .async(DishDetailsPager.class);
    }
}
