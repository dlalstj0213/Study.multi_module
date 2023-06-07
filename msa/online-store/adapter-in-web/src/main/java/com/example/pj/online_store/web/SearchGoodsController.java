package com.example.pj.online_store.web;

import com.common.session.CommonSessionTemplate;
import com.common.session.exception.CommonSessionException;
import com.common.session.model.UserSession;
import com.example.pj.online_shop.command.GetGoodsListCommand;
import com.example.pj.online_shop.query.GetGoodsDetailQuery;
import com.example.pj.online_shop.query.GetGoodsListQuery;
import com.example.pj.online_store.Goods;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/auth")
    String testAuth(@RequestParam String username) {

        UserSession userSession = commonSessionTemplate.createSessionBy(new UserSession(UUID.randomUUID().toString(),
                                                                                        username,
                                                                                        "dlalstj0213@gmail.com"));
        String id = commonSessionTemplate.getSession().orElseThrow().getId();
        String response = """
                userSession: %s
                sessionId: %s
                """;
        return String.format(response, userSession, id);
    }

    @GetMapping("/check")
    String checkAuth() throws CommonSessionException {
        UserSession user = commonSessionTemplate.getUserSession();
        return user.toString();
    }

    @GetMapping("/find-session/{id}")
    void loginId(@PathVariable String id) {
        commonSessionTemplate.removeSession(id);
    }

    @PostMapping("/clear")
    void clear() {
        commonSessionTemplate.removeSession();
    }

}