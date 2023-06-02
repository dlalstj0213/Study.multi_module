package com.example.pj.online_store.web;

import com.example.pj.online_shop.command.GetGoodsListCommand;
import com.example.pj.online_shop.query.GetGoodsDetailQuery;
import com.example.pj.online_shop.query.GetGoodsListQuery;
import com.example.pj.online_store.Goods;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;


@OnlineStoreRestApi
@RequiredArgsConstructor
public class SearchGoodsController {

    private final GetGoodsDetailQuery getGoodsDetailQuery;
    private final GetGoodsListQuery getGoodsListQuery;

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
    String testAuth(@RequestBody HashMap<String, Object> data, HttpSession session) {
        String username = (String) data.get("username");
        session.setAttribute("username", username);
        String response = """
                sessionId: %s
                username: %s
                """;
        return String.format(response, session.getId(), username);
    }

    @PostMapping("/check")
    String checkAuth(HttpSession session) {
        Object obj = session.getAttribute("username");
        if (obj != null) return (String) obj;
        return "No Value";
    }

    @PostMapping("/clear")
    void clear(HttpSession session) {
        session.invalidate();
    }
}