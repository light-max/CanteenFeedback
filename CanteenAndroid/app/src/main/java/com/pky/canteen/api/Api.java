package com.pky.canteen.api;

import com.pky.canteen.async.Async;
import com.pky.canteen.data.result.CollectorPager;
import com.pky.canteen.data.result.DishDetails;
import com.pky.canteen.data.result.MenuItemPager;
import com.pky.canteen.data.result.RemarkPager;
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
}
