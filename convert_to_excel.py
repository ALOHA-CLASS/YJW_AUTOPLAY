import pandas as pd

# CSV 파일 경로
csv_file_path = '/e:/알로하소프트/DEV/양주원님/CODE/YJW_AUTOPLAY/MDTI.csv'
# Excel 파일 경로
excel_file_path = '/e:/알로하소프트/DEV/양주원님/CODE/YJW_AUTOPLAY/MDTI.xlsx'

# CSV 파일을 읽어 DataFrame으로 변환
df = pd.read_csv(csv_file_path)

# DataFrame을 Excel 파일로 저장
df.to_excel(excel_file_path, index=False)
