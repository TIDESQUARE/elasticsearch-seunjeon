package org.bitbucket.eunjeon.seunjeon

object Pos extends Enumeration {
  type Pos = Value
  // 품사 태그 설명
  //  https://docs.google.com/spreadsheets/d/1-9blXKjtjeKZqsf4NzHeYJCrr49-nXeRF6D80udfcwY/edit#gid=589544265&vpid=A1
  val BOS, EOS, UNKNOWN, COMPOUND, INFLECT, PREANALYSIS,
      EP, // 선어말어미,
      E,  // 어미
      I,  // 독립언
      J,  // 관계언
      M,  // 수식언
      N,  // 체언
      S,  // 부호
      SL, // 외국어
      SH, // 한자
      SN, // 숫자
      V,  // 용언
      VCP,  // 긍정지정사
      XP, // 접두사,
      XS, // 접미사
      XR  // 어근
      = Value

  val matchTable = Map(
  "UNKNOWN" -> UNKNOWN,
  "EP" -> EP,
  "EC" -> E,
  "EF" -> E,
  "ETM" -> E,
  "ETN" -> E,
  "IC" -> I,
  "JC" -> J,
  "JKB" -> J,
  "JKC" -> J,
  "JKG" -> J,
  "JKO" -> J,
  "JKQ" -> J,
  "JKS" -> J,
  "JKV" -> J,
  "JX" -> J,
  "MAG" -> M,
  "MAJ" -> M,
  "MM" -> M,
  "NNG" -> N,
  "NNP" -> N,
  "NNB" -> N,
  "NNBC" -> N,
  "NP" -> N,
  "NR" -> N,
  "SL" -> SL,
  "SH" -> SH,
  "SN" -> SN,
  "SF" -> S,
  "SP" -> S,
  "SSC" -> S,
  "SSO" -> S,
  "SC" -> S,
  "SY" -> S,
  "SE" -> S,
  "VCP" -> VCP,
  "VA" -> V,
  "VCN" -> V,
  "VV" -> V,
  "VX" -> V,
  "XPN" -> XP,
  "XSA" -> XS,
  "XSN" -> XS,
  "XSV" -> XS,
  "XR" -> XR
  )

  def apply(feature:Seq[String]): Pos = {
    if (feature(4) == "*") {
      matchTable(feature(0))
    } else {
      feature(4) match {
        case "Compound" => Pos.COMPOUND
        case "Preanalysis" => Pos.PREANALYSIS
        case "Inflect" => Pos.INFLECT
      }
    }
  }

  def poses(feature:Seq[String]): IndexedSeq[Pos] = {
    feature(0).split("[+]").map(matchTable(_))
  }

  private def isMatch(pattern:Seq[String], feature:Seq[String]): Boolean = {
    for (idx <- pattern.indices) {
      val patternChar = pattern(idx)
      if (patternChar != "*" && patternChar != feature(idx)) return false
    }
    true
  }
}
