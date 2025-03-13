package com.backendApi.board.dto.postdto;

import com.backendApi.board.domain.Post;
import lombok.Data;

@Data
public class ResponsePostByGetBoardDto implements ResponsePostDto {

    private Long id;

    private String title;

    private String nickname;

    public ResponsePostByGetBoardDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.nickname = post.getMember().getNickname();
    }


}
