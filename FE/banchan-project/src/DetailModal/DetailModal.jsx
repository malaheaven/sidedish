import DetailModalWindow from "./DetailModalWindow/DetailModalWindow";
import * as S from "./DetailModalStyles";

const DetailModal = ({
  detailUrl,
  modalFlag,
  handleModalFlag,
  handleClickCard,
}) => {
  return (
    modalFlag && (
      <>
        <DetailModalWindow
          detailUrl={detailUrl}
          handleModalFlag={handleModalFlag}
          handleClickCard={handleClickCard}
        />
        <S.DetailModalBackground />
      </>
    )
  );
};

export default DetailModal;
