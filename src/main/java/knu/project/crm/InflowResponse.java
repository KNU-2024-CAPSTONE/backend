package knu.project.crm;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter
public class InflowResponse {
    private String period;
    private List<WeeklyData> weeklyData;

    // Getter and Setter

    public static class WeeklyData {
        private String week;
        private Map<String, AgeGroup> ageGroups;

        // Getter and Setter
    }

    public static class AgeGroup {
        private int male;
        private int female;

        // Getter and Setter
    }
}
