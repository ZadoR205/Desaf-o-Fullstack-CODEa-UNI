package codea.uni.desafio_fullstack.machinery.interfaces.rest.resources;

public record UpdateMachineryMachineryTypeResource(Integer machineryTypeId) {
    public UpdateMachineryMachineryTypeResource {
        if (machineryTypeId == null) {
            throw new IllegalArgumentException("MachineryTypeId cannot be null");
        }
    }
}
