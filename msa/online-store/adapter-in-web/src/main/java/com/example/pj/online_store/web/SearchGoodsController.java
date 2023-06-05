package com.example.pj.online_store.web;

import com.autoconfigure.SampleServiceInterface;
import com.common.session.CommonSessionTemplate;
import com.common.session.model.UserSession;
import com.example.pj.online_shop.command.GetGoodsListCommand;
import com.example.pj.online_shop.query.GetGoodsDetailQuery;
import com.example.pj.online_shop.query.GetGoodsListQuery;
import com.example.pj.online_store.Goods;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@OnlineStoreRestApi
@RequiredArgsConstructor
public class SearchGoodsController {

    private final GetGoodsDetailQuery getGoodsDetailQuery;
    private final GetGoodsListQuery getGoodsListQuery;
    private final CommonSessionTemplate commonSessionTemplate;

    @GetMapping("")
    ResponseEntity<List<SearchGoodsResponse>> getGoodsList() {
        GetGoodsListCommand command = new GetGoodsListCommand("", 0, 5, "name", "ASC");
        List<Goods> resultList = getGoodsListQuery.getGoodsList(command);

        return ResponseEntity.ok().body(resultList.stream()
                                                  .map(item -> new SearchGoodsResponse(item.getModelId(),
                                                                                       item.getGoodsName()))
                                                  .toList());
    }

    @GetMapping("/{id}")
    ResponseEntity<SearchGoodsResponse> getGoodsDetail(@PathVariable String id) {
        Goods goods = getGoodsDetailQuery.getGoodsDetail(new Goods.GoodsId(Long.parseLong(id)));
        return ResponseEntity.ok(new SearchGoodsResponse(goods.getModelId(), goods.getGoodsName()));
    }

    @PostMapping("/auth")
    String testAuth(@RequestBody HashMap<String, Object> data) {
        String username = (String) data.get("username");

        UserSession userSession = commonSessionTemplate.createSessionBy(new UserSession(UUID.randomUUID().toString(),
                                                                                        username,
                                                                                        "dlalstj0213@gmail.com"));

        String response = """
                userSession: %s
                """;
        return String.format(response, userSession);
    }

    @PostMapping("/check")
    String checkAuth(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return "No Session";
        Object obj = session.getAttribute("username");
        if (obj != null) return (String) obj;
        return "No Value";
    }

    @PostMapping("/login/{id}")
    String loginId(@PathVariable String id) {
        return id;
    }

    @PostMapping("/clear")
    void clear(HttpSession session) {
        session.invalidate();
    }

}