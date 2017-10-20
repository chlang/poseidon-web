package edu.mum.se.poseidon.web.services.dto.Schedule;

import edu.mum.se.poseidon.web.services.dto.BlockDto;
import edu.mum.se.poseidon.web.services.dto.SectionDto;

import java.util.ArrayList;
import java.util.List;

public class BlockTrack {
    private BlockDto block;
    private int nStudent;
    private List<SectionDto> sections = new ArrayList<SectionDto>();

    public BlockTrack(BlockDto block, int nStudent) {
        this.block = block;
        this.nStudent = nStudent;
    }

    public void addSection(SectionDto section) {
        this.sections.add(section);
    }

    public BlockDto getBlock() {
        return block;
    }

    public void setBlock(BlockDto block) {
        this.block = block;
    }

    public int getnStudent() {
        return nStudent;
    }

    public void setnStudent(int nStudent) {
        this.nStudent = nStudent;
    }

    public List<SectionDto> getSections() {
        return sections;
    }
}