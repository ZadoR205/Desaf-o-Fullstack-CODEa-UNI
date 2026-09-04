package codea.uni.desafio_fullstack.machinery.domain.model.commands;

public record UpdateMachineryHourMeterCommand(float hours) {
    public UpdateMachineryHourMeterCommand {
        if(hours < 0){
            throw new IllegalArgumentException("Hours and Hours must be non-negative");
        }
    }
}
