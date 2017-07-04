import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * Created by dy on 2017/7/4.
 */
public class TestPipeline implements Pipeline {
    public TestPipeline() {
    }

    public void process(ResultItems resultItems, Task task) {

        Map<String, Object> map =  resultItems.getAll();
        DyygsCell dyygsCell = (DyygsCell) map.get("dyygsCell");
        System.out.println("get page: " + resultItems.getRequest().getUrl() + "\ntitle" + dyygsCell.getTitle()
                + "\ncontent" + dyygsCell.getContent() + "\ndate" + dyygsCell.getDate());
    }
}
