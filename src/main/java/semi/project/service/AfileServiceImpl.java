package semi.project.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import semi.project.domain.AfileVo;
import semi.project.mapper.AfileMapper;

import java.util.List;


@Service
@Log4j
public class AfileServiceImpl implements AfileService {
	private AfileMapper afileMapper;

	public AfileServiceImpl(AfileMapper afileMapper){
		this.afileMapper = afileMapper;
	}


	@Override
	public List<AfileVo> selectAfileListBybSeq(long bseq) {
		return afileMapper.selectAfileListBybSeq(bseq);
	}

	@Override
	public List<AfileVo> selectMyfileList(AfileVo afileVo) {
		return afileMapper.selectMyfileList(afileVo);
	}

















	@Override
	public void fileUploadS(AfileVo afileVo) {
		// TODO Auto-generated method stub
		log.info("앞뒤로");
		log.info("이쯤에서 afile: "+afileVo);
		afileMapper.fileUpload(afileVo);
		log.info("포위");
	}

	@Override
	public List<AfileVo> afileSelectBySeqS(long bseq) {
		return afileMapper.afileSelectBySeq(bseq);
	}


	@Override
	public List<AfileVo> afileSelectBySids(AfileVo afileVo) {
		log.info("로그를 몇번 찍는건지");
		return afileMapper.afileSelectBySid(afileVo);
	}

	@Override
	public void deleteByAfName(String afname) {
		afileMapper.deleteByAfName(afname);
	}

}
