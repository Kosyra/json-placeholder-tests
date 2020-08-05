package apiTests.testData;

import dto.PostRequestDto;

public class PostRequestBody {
    public PostRequestDto GetRandomPostData(){
        PostRequestDto data = new PostRequestDto();
        data.setUserId(111);
        data.setTitle("Test title");
        data.setBody("Test body for POST request");
        return data;
    }

    public PostRequestDto GetExistingPostData(){
        PostRequestDto data = new PostRequestDto();
        data.setUserId(1);
        data.setTitle("Tytuł po edycji postu");
        data.setBody("Treść po edycji postu");
        return data;
    }
}
