package task;

public enum UserTaskActionType {
    NEXT("去下一步"),COMPLETE("完成任务");
    private String description;
    private UserTaskActionType(String decription){
        this.description=decription;
    }

}
